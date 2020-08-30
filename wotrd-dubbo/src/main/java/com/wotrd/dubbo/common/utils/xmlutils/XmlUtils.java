package com.wotrd.dubbo.common.utils.xmlutils;



import com.wotrd.dubbo.common.utils.xmlutils.domain.Person;
import com.wotrd.dubbo.common.utils.xmlutils.domain.PersonList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

public class XmlUtils {
    public static void main(String[] args) {
        Person person= new Person();
        person.setAge(1);
        person.setName("tomc");
        person.setId(1L);
        ArrayList personList = new ArrayList();
        personList.add(person);
        personList.add(person);
        PersonList personList1 = new PersonList();
        personList1.setPerson(personList);
        String s = bean2xml(personList1);
        System.out.println(s);
        String s1= "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "\n" +
                "&lt;persons&gt;\n" +
                "    &lt;person&gt;\n" +
                "        &lt;id&gt;1&lt;/id&gt;\n" +
                "        <name>tomc</name>\n" +
                "        <age>1</age>\n" +
                "    </person>\n" +
                "</persons>";
        PersonList person1 = xml2bean(s1, PersonList.class);
        System.out.println(person1.getPerson().size());
    }

    /**
     * 将XML转为实体
     *
     * @param xml
     * @param c
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T xml2bean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * JavaBean转换成xml
     *
     * @param obj
     * @return
     */
    public static String bean2xml(Object obj) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
