package com.fhzz.springbootdemo.service.attendance.impl;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fhzz.springbootdemo.dao.master.attendance.jpa.AttendanceJpa;
import com.fhzz.springbootdemo.entity.master.attendance.TAttendance;
import com.fhzz.springbootdemo.service.attendance.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	private static final String uploadPath = "E:/uploadFile";

	@PostConstruct
	public void init() {
		File dest = new File(uploadPath);
		if (!dest.exists()) { // 判断文件父目录是否存在
			dest.mkdir();
		}
	}

	@Autowired
	private AttendanceJpa attendanceJpa;

	private boolean isAttendanceExist(String year, String month) {
		return attendanceJpa.findByYearAndMonth(year, month) != null;
	}

	private void saveTAttendance(String year, String month, String fileName) {
		TAttendance attendance = new TAttendance();
		attendance.setYear(year);
		attendance.setMonth(month);
		attendance.setFileName(fileName);
		attendanceJpa.save(attendance);
	}

	@SuppressWarnings("deprecation")
	@Override
	public String upload(MultipartFile fileInput, String year, String month) {
		// 1查询是否已经存在
		// if (this.isAttendanceExist(year, month)) {
		// return "exist";
		// }
		// 2保存文件
		String fileName = fileInput.getOriginalFilename();
		File dest = new File(uploadPath + "/" + fileName);
		try {
			fileInput.transferTo(dest); // 保存文件
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		// 3写数据库
		this.saveTAttendance(year, month, fileName);
		// 4解析文件
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook(new POIFSFileSystem(fileInput.getInputStream()));
			int numberOfSheets = workbook.getNumberOfSheets();
			HSSFSheet sheet = workbook.getSheetAt(numberOfSheets - 1);
			int numberOfRows = sheet.getPhysicalNumberOfRows();
			for (int i = 3; i < numberOfRows; i++) {
				HSSFRow row = sheet.getRow(i);
				int physicalNumberOfCells = row.getPhysicalNumberOfCells();
				for (int j = 0; j < physicalNumberOfCells; j++) {
					HSSFCell cell = row.getCell(j);
					
					
					if (j > 3 ) {
						if(CellType.NUMERIC.getCode() == cell.getCellType()){
							System.out.println(String.valueOf(cell.getNumericCellValue()));
						}else if(CellType.STRING.getCode() == cell.getCellType()){
							System.out.println(String.valueOf(cell.getStringCellValue()));
						}
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "true";

	}

}
