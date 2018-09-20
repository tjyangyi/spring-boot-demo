package com.fhzz.springbootdemo.service.attendance.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fhzz.springbootdemo.dao.master.attendance.jpa.AttendanceJpa;
import com.fhzz.springbootdemo.dao.master.attendance.jpa.TAttendanceDetailJpa;
import com.fhzz.springbootdemo.dao.master.attendance.jpa.TAttendanceRecordJpa;
import com.fhzz.springbootdemo.entity.master.attendance.TAttendance;
import com.fhzz.springbootdemo.entity.master.attendance.TAttendanceDetail;
import com.fhzz.springbootdemo.entity.master.attendance.TAttendanceRecord;
import com.fhzz.springbootdemo.service.attendance.AttendanceService;
import com.fhzz.springbootdemo.service.sercurity.TUserService;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	private static String uploadPath = null;

	@PostConstruct
	public void init() throws FileNotFoundException {
		ApplicationHome home = new ApplicationHome(getClass());
		File jarFile = home.getSource();
		uploadPath = jarFile.getParentFile().toString();

		uploadPath = uploadPath + "/uploadFile/";
		System.out.println(uploadPath);
		File dest = new File(uploadPath);
		if (!dest.exists()) { // 判断文件父目录是否存在
			dest.mkdir();
		}
	}

	@Autowired
	private AttendanceJpa attendanceJpa;

	@Autowired
	private TAttendanceDetailJpa attendanceDetailJpa;

	@Autowired
	private TAttendanceRecordJpa attendanceRecordJpa;

	@Autowired
	private TUserService userService;

	@Override
	public List<TAttendance> getAllList() {
		return attendanceJpa.findAll();
	}

	@Override
	public List<TAttendanceDetail> queryAttendanceDetails(String attendanceId) {
		return attendanceDetailJpa.findByAttendaceIdOrderByIndexNumberAsc(attendanceId);
	}

	@Override
	public String getTitleTip(String year, String month) {
		TAttendance tAttendance = attendanceJpa.findByYearAndMonth(year, month);
		if (tAttendance != null) {
			return tAttendance.getTitle();
		} else {
			return "";
		}
	}

	@Override
	public List<TAttendanceDetail> queryAttendanceDetailsByYearMonth(String year, String month) {
		TAttendance attendance = attendanceJpa.findByYearAndMonth(year, month);
		if (attendance == null) {
			return new ArrayList<TAttendanceDetail>();
		}
		return this.queryAttendanceDetails(attendance.getId());
	}

	@Override
	public List<TAttendanceDetail> queryAttendanceDetailsByYearMonthUsername(String year, String month,
			String username) {
		TAttendance attendance = attendanceJpa.findByYearAndMonth(year, month);
		if (attendance == null) {
			return new ArrayList<TAttendanceDetail>();
		}
		return attendanceDetailJpa.findByAttendaceIdAndUsernameOrderByIndexNumberAsc(attendance.getId(), username);
	}

	@Override
	public TAttendanceDetail queryTAttendanceDetailById(String id) {
		Optional<TAttendanceDetail> o = attendanceDetailJpa.findById(id);
		return o.get();
	}

	@Override
	public void modifyAttendanceDetails(TAttendanceDetail attendanceDetail, String loginIp) {
		this.recordModifyTAttendanceDetail(attendanceDetail, loginIp);
		attendanceDetailJpa.save(attendanceDetail);
	}

	private void recordModifyTAttendanceDetail(TAttendanceDetail attendanceDetail, String loginIp) {
		Optional<TAttendanceDetail> o = attendanceDetailJpa.findById(attendanceDetail.getId());
		TAttendanceDetail old = o.get();
		TAttendanceRecord record = new TAttendanceRecord();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		record.setUsername(userDetails.getUsername());
		record.setLoginIp(loginIp);
		record.setModifyDetailId(attendanceDetail.getId());
		record.setPreB(old.getB());
		record.setPreC(old.getC());
		record.setPreG(old.getG());
		record.setTargetUsername(attendanceDetail.getUsername());
		record.setNewB(attendanceDetail.getB());
		record.setNewC(attendanceDetail.getC());
		record.setNewG(attendanceDetail.getG());
		record.setModifyTime(new Date());
		attendanceRecordJpa.save(record);
	}

	@Override
	public File generateNewFile(String attendanceId) {
		Optional<TAttendance> optional = attendanceJpa.findById(attendanceId);
		TAttendance aAttendance = optional.get();
		String fileName = aAttendance.getFileName();// 获取当前保存的文件名
		HSSFWorkbook workbook = null;
		FileInputStream excelFileInputStream = null;
		FileOutputStream excelFileOutputStream = null;
		try {
			excelFileInputStream = new FileInputStream(uploadPath + fileName);// 读取原excel
			excelFileOutputStream = new FileOutputStream(uploadPath + "新_" + fileName);// 写入新excel
			workbook = new HSSFWorkbook(new POIFSFileSystem(excelFileInputStream));
			HSSFSheet sheet = workbook.getSheetAt(workbook.getNumberOfSheets() - 1);// 获取最后一个sheet页
			for (int i = 3, numberOfRows = sheet.getPhysicalNumberOfRows(); i < numberOfRows; i++) {
				HSSFRow row = sheet.getRow(i);
				this.buildExcelRow(row, attendanceId);// 根据数据库中的数据，刷新excel的每一行
			}
			workbook.write(excelFileOutputStream);
			excelFileOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (excelFileOutputStream != null)
					excelFileOutputStream.close();
				if (excelFileInputStream != null)
					excelFileInputStream.close();
				if (workbook != null)
					workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new File(uploadPath + "新_" + fileName); // 将新写入的excel返回供下载
	}

	@Override
	public String upload(MultipartFile fileInput, String year, String month) {
		// 1查询是否已经存在
		if (this.isAttendanceExist(year, month)) {
			return "文件已存在";
		}
		// 2保存文件
		String fileName = year + "_" + month + "_" + fileInput.getOriginalFilename();
		File dest = new File(uploadPath + fileName);
		try {
			fileInput.transferTo(dest); // 保存文件
		} catch (Exception e) {
			e.printStackTrace();
			return "保存文件失败";
		}
		// 3构建考勤
		TAttendance attendance = this.buildTAttendance(year, month, fileName);
		// 4解析文件
		HSSFWorkbook workbook = null;
		FileInputStream excelFileInputStream = null;
		try {
			// 4解析EXCEL文件
			excelFileInputStream = new FileInputStream(uploadPath + fileName);
			workbook = new HSSFWorkbook(new POIFSFileSystem(excelFileInputStream));
			HSSFSheet sheet = workbook.getSheetAt(workbook.getNumberOfSheets() - 1);
			// 5保存考勤
			attendance.setTitle(sheet.getRow(1).getCell(0).getStringCellValue());
			attendance = attendanceJpa.save(attendance);
			for (int i = 3, numberOfRows = sheet.getPhysicalNumberOfRows(); i < numberOfRows; i++) {
				HSSFRow row = sheet.getRow(i);
				TAttendanceDetail detail = buildTAttendanceDetail(attendance.getId(), row);
				// 6保存考勤详细
				attendanceDetailJpa.save(detail);
				userService.saveNewUser(detail.getUsername(), detail.getRealname());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (excelFileInputStream != null)
					excelFileInputStream.close();
				if (workbook != null)
					workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "true";
	}

	private boolean isAttendanceExist(String year, String month) {
		return attendanceJpa.findByYearAndMonth(year, month) != null;
	}

	private TAttendance buildTAttendance(String year, String month, String fileName) {
		TAttendance attendance = new TAttendance();
		attendance.setYear(year);
		attendance.setMonth(month);
		attendance.setFileName(fileName);
		return attendance;
	}

	private TAttendanceDetail buildTAttendanceDetail(String attendanceId, HSSFRow row) {
		TAttendanceDetail detail = new TAttendanceDetail();
		detail.setAttendaceId(attendanceId);
		detail.setIndexNumber((int) row.getCell(0).getNumericCellValue());
		row.getCell(4).setCellType(CellType.STRING);
		detail.setUsername(row.getCell(4).getStringCellValue());
		detail.setRealname(row.getCell(5).getStringCellValue());
		detail.setCardNumber(row.getCell(6).getStringCellValue());
		detail.setA((int) row.getCell(7).getNumericCellValue());
		if (row.getCell(8).getCellTypeEnum() == CellType.NUMERIC)
			detail.setB((int) row.getCell(8).getNumericCellValue());
		if (row.getCell(9).getCellTypeEnum() == CellType.NUMERIC)
			detail.setC((int) row.getCell(9).getNumericCellValue());
		if (row.getCell(10).getCellTypeEnum() == CellType.NUMERIC)
			detail.setD((int) row.getCell(10).getNumericCellValue());
		if (row.getCell(11).getCellTypeEnum() == CellType.NUMERIC)
			detail.setE((int) row.getCell(11).getNumericCellValue());
		detail.setF((int) row.getCell(12).getNumericCellValue());
		detail.setG((int) row.getCell(13).getNumericCellValue());
		return detail;
	}

	private void buildExcelRow(HSSFRow row, String attendanceId) {
		HSSFCell cell = row.getCell(4);
		cell.setCellType(CellType.STRING);
		String username = cell.getStringCellValue();
		TAttendanceDetail attendanceDetail = attendanceDetailJpa.findByAttendaceIdAndUsername(attendanceId, username);
		Integer b = attendanceDetail.getB();
		if (b != null)
			row.getCell(8).setCellValue(b);
		Integer c = attendanceDetail.getC();
		if (c != null)
			row.getCell(9).setCellValue(c);
		Integer d = attendanceDetail.getD();
		row.getCell(10).setCellValue(d);
		Integer e = attendanceDetail.getE();
		row.getCell(11).setCellValue(e);
		Integer f = attendanceDetail.getF();
		row.getCell(12).setCellValue(f);
		Integer g = attendanceDetail.getG();
		row.getCell(13).setCellValue(g);
	}

}
