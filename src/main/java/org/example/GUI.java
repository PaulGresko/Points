package org.example;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class GUI {
    JFrame frame;
    private JPanel Panel;
    private JPanel Points;
    private JPanel Log;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JTextPane textPane1;
    private JButton button1;
    private JTable table2;
    private JButton saveLogButton;
    private JButton uploadLogButton;

    public GUI() {

        TableDrawPoint();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = textPane1.getText();
                UploadPoints.upload(filename);
                TableDrawPoint();
            }
        });

        uploadLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableDrawLog();
            }
        });

        saveLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SaveToExcel.Save(table2);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(frame, "log saved!");
            }
        });
    }


            public void run() {
                frame = new JFrame();
                frame.setLocation(600,300);
                frame.setName("Dots");
                frame.setContentPane(new GUI().Panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setSize(800, 500);
            }



    public void TableDrawLog(){
        String[] columnNames = {"Record ID", "Calculation ID", "Start time", "End time", "A(x, y, z)", "B(x, y, z)", "Method", "Distance"};
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createSQLQuery(
                "select record.ID, calculation_ID, t1, t2, method, S from record,calculation where record.calculation_ID = calculation.ID;");

        List<Object[]> list = query.list();
        Object[][] data = new Object[list.size()][8];
        for(int i = 0; i < list.size(); i++){
            data[i][0] = list.get(i)[0].toString();
            data[i][1] = list.get(i)[1].toString();
            data[i][2] = list.get(i)[2].toString();
            data[i][3] = list.get(i)[3].toString();

            TableModel model = table1.getModel();
            data[i][4] = model.getValueAt(i,0).toString();
            data[i][5] = model.getValueAt(i,1).toString();

            data[i][6] = list.get(i)[4].toString();
            data[i][7] = list.get(i)[5].toString();
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table2.setModel(model);
        table2.repaint();
        Panel.repaint();
        session.close();
    }
    private void TableDrawPoint() {
        String[] columnNames = {"A(x, y, z)", "B(x, y, z)", "S"};


        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createSQLQuery(
                "select x, y, z, S from point, record where record_ID = record.ID order by record_ID;");
        List<Object[]> list = query.list();

        Object[][] data = new Object[list.size()/2][3];

        for(int i = 0; i < list.size()/2; i++){
            if(list.get(i*2)[2] != null) {
                data[i][0] = ("A( " + list.get(i*2)[0] + ", " + list.get(i*2)[1] + ", " + list.get(i*2)[2] + ")").toString();
                data[i][1] = ("B( " + list.get(i*2+1)[0] + ", " + list.get(i*2+1)[1] + ", " + list.get(i*2+1)[2] + ")").toString();
            }
            else{
                data[i][0] = ("A( " + list.get(i*2)[0] + ", " + list.get(i*2)[1] + ")").toString();
                data[i][1] = ("B( " + list.get(i*2+1)[0] + ", " + list.get(i*2+1)[1] + ")").toString();
            }
            data[i][2] = list.get(i*2)[3].toString();
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table1.setModel(model);
        table1.repaint();
        Panel.repaint();
        session.close();
    }

}

