package com.wotrd.dubbo.common.utils.xmlutils.domain;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "person")
@Data
public class Person {

    private Long id;

    private String name;

    private int age;
}
