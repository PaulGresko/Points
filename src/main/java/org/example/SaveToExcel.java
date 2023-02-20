package org.example;

import javafx.collections.ObservableList;
import org.apache.poi.hssf.usermodel.*;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class SaveToExcel {


    public static void Save(JTable table) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet spreadsheet = workbook.createSheet("Log");
        HSSFRow row = null;
        TableModel  model = table.getModel();

        for (int i = 0; i < table.getRowCount(); i++){
            row = spreadsheet.createRow(i);
            for (int j = 0; j < table.getColumnCount(); j++) {
                row.createCell(j).setCellValue(model.getValueAt(i, j).toString());
            }
        }

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream( "Log.xls");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
