package org.example;

import org.hibernate.Session;
import org.hibernate.query.Query;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI {
    private JPanel Panel;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JTextPane textPane1;
    private JButton button1;
    private JTable table2;

    public GUI() {
        TableDrawPoint();
        TableDrawLog();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = textPane1.getText();
                UploadPoints.upload(filename);
                TableDrawLog();
                TableDrawPoint();
            }
        });
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setContentPane(new GUI().Panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setSize(800, 500);
            }
 //       });
 //   }


    public void TableDrawLog(){
        String[] columnNames = {"Record ID", "Calculation ID", "Start time", "End time", "A(x, y, z)", "B(x, y, z)", "Method", "Distance"};
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createSQLQuery(
                "select pointa.record_ID, record.calculation_ID, t1, t2, pointa.x as Ax, pointa.y as Ay, pointa.z as Az, pointb.x, pointb.y, pointb.z, calculation.method, record.S\n" +
                        "from points.record, points.calculation, points.pointa, points.pointb" +
                        " where record.calculation_ID =  calculation.ID and pointa.record_ID = record.ID and pointb.record_ID = record.ID;");

        List<Object[]> list = query.list();
        Object[][] data = new Object[list.size()][8];
        for(int i = 0; i < list.size(); i++){
            data[i][0] = list.get(i)[0].toString();
            data[i][1] = list.get(i)[1].toString();
            data[i][2] = list.get(i)[2].toString();
            data[i][3] = list.get(i)[3].toString();
            data[i][4] = ("A( " + list.get(i)[4] + ", " + list.get(i)[5] + ", " + list.get(i)[6] + ")").toString();
            data[i][5] = ("B( " + list.get(i)[7] + ", " + list.get(i)[8] + ", " + list.get(i)[9] + ")").toString();
            data[i][6] = list.get(i)[10].toString();
            data[i][7] = list.get(i)[11].toString();
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
        Query query = session.createSQLQuery("select pointa.x as Ax , pointa.y as Ay , pointa.z as Az,  pointb.x , pointb.y , pointb.z, record.S from points.pointa, points.pointb, points.record where pointb.record_ID = pointa.record_ID and pointa.record_ID = record.ID;");
        List<Object[]> list = query.list();

        Object[][] data = new Object[list.size()][3];
        for(int i = 0; i < list.size(); i++){
            data[i][0] = ("A( " + list.get(i)[0] + ", " + list.get(i)[1] + ", " + list.get(i)[2] + ")").toString();
            data[i][1] = ("B( " + list.get(i)[3] + ", " + list.get(i)[4] + ", " + list.get(i)[5] + ")").toString();
            data[i][2] = list.get(i)[6].toString();
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table1.setModel(model);
        table1.repaint();
        Panel.repaint();
        session.close();
    }
}

