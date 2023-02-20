package org.example;


import org.example.entity.Point;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.w3c.dom.*;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Time;


public class UploadPoints {
    private String filename;
    public static void upload(String filename) {


        Session session = HibernateUtil.getSessionFactory().openSession();

        session.getTransaction().begin();


        session.createSQLQuery("insert into calculation(t1) values(\'" + new Time(System.currentTimeMillis()) +"\');").executeUpdate();
        session.getTransaction().commit();

        Query query = session.createSQLQuery("select max(ID) from calculation");
        Integer calcID = Integer.valueOf(query.getSingleResult().toString());

        Point A = new Point();
        Point B = new Point();

        String method = "";
        Double distance = 0.0;

        try {
            File xmlDoc = new File(filename);
            DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbFact.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlDoc);

            NodeList nodeList = document.getElementsByTagName("point");

            for(int i = 0; i < nodeList.getLength()/2; i++) {
                session.getTransaction().begin();
                session.createSQLQuery("insert into record (calculation_ID) values(" + calcID + ");").executeUpdate();
                session.getTransaction().commit();
            }
            query = session.createSQLQuery("select max(ID) from record");
            Integer count = Integer.valueOf(query.getSingleResult().toString()) + 1 - nodeList.getLength() / 2;
            for(int i = 0; i < nodeList.getLength(); i++) {


                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute("name").equals("A")) {
                        A.setX(Double.valueOf(element.getElementsByTagName("X").item(0).getTextContent()));
                        A.setY(Double.valueOf(element.getElementsByTagName("Y").item(0).getTextContent()));
                        if(element.getElementsByTagName("Z").item(0) != null) {
                            A.setZ(Double.valueOf(element.getElementsByTagName("Z").item(0).getTextContent()));
                        }else { A.setZ(null); }
                        session.getTransaction().begin();
                        session.createSQLQuery("insert into point(x,y,z,record_ID) values("+
                                A.getX() + "," + A.getY() + "," + A.getZ() + "," + count +");").executeUpdate();
                        session.getTransaction().commit();
                    }

                    if (element.getAttribute("name").equals("B")) {
                        B.setX(Double.valueOf(element.getElementsByTagName("X").item(0).getTextContent()));
                        B.setY(Double.valueOf(element.getElementsByTagName("Y").item(0).getTextContent()));
                        if(element.getElementsByTagName("Z").item(0) != null) {
                            B.setZ(Double.valueOf(element.getElementsByTagName("Z").item(0).getTextContent()));
                        }else { B.setZ(null); }
                        session.getTransaction().begin();
                        session.createSQLQuery("insert into point(x,y,z,record_ID) values("+
                                B.getX() + "," + B.getY() + "," + B.getZ() + "," + count + ");").executeUpdate();
                        session.getTransaction().commit();


                    if(A.getZ() == null){
                        method = "2D-distance";
                        distance = CalculationDistance.calculationDistance2(A.getX(),A.getY(),B.getX(),B.getY());
                    }
                    else {
                        method = "3D-distance";
                        distance = CalculationDistance.calculationDistance3(A.getX(), A.getY(), A.getZ(), B.getX(), B.getY(), B.getZ());
                    }
                        session.getTransaction().begin();
                        session.createSQLQuery("update record set S ="+ distance + " where ID = " + count++ + ";").executeUpdate();
                        session.getTransaction().commit();
                    }
                }
            }
            session.getTransaction().begin();
            session.createSQLQuery("update calculation set method = \'" + method + "\', t2 = \'"+ new Time(System.currentTimeMillis()) + "\' where ID = "+ calcID +";" ).executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {}
    }
}
