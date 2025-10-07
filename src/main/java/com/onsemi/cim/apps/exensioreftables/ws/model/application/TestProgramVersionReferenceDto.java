package com.onsemi.cim.apps.exensioreftables.ws.model.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.TestProgramVersionReference;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

/**
 * @author Pavel Outericky, fg7ngj
 * 12/19/2024
 */
@XmlRootElement(name = "TestProgramVersionReference")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TestProgramVersionReferenceDto {

    private String programName;
    private String parametersHash;
    private Long programVersion;
    private Date insertTime;

    public TestProgramVersionReferenceDto(TestProgramVersionReference testProgramVersionReference) {
        this.programName = testProgramVersionReference.getProgramName();
        this.parametersHash = testProgramVersionReference.getParametersHash();
        this.programVersion = testProgramVersionReference.getProgramVersion();
        this.insertTime = testProgramVersionReference.getInsertTime();
    }


    @XmlAttribute(name="ProgramName")
    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    @XmlAttribute(name="ParametersHash")
    public String getParametersHash() {
        return parametersHash;
    }

    public void setParametersHash(String parametersHash) {
        this.parametersHash = parametersHash;
    }

    @XmlAttribute(name="ProgramVersion")
    public Long getProgramVersion() {
        return programVersion;
    }

    public void setProgramVersion(Long programVersion) {
        this.programVersion = programVersion;
    }

    @XmlAttribute(name="InsertTime")
    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    @Override
    public String toString() {
        return "TestProgramVersionReferenceDto{" +
                "programName='" + programName + '\'' +
                ", parametersHash='" + parametersHash + '\'' +
                ", programVersion=" + programVersion +
                ", insertTime=" + insertTime +
                '}';
    }
}

