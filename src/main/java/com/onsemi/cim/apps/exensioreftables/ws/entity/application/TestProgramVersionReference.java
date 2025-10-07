package com.onsemi.cim.apps.exensioreftables.ws.entity.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.ApplicationEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

/**
 * @author Pavel Outericky, fg7ngj
 * 12/19/2024
 */

@Entity(name = "TEST_PROGRAM_VER_REFERENCE")
public class TestProgramVersionReference extends ApplicationEntity implements Cloneable {

    @Column(name = "PROGRAM_NAME ") private String programName;
    @Column(name = "PARAMETERS_HASH") private String parametersHash;
    @Column(name = "PROGRAM_VERSION") private Long programVersion;
    @Column(name = "INSERT_TIME") private Date insertTime;

    public TestProgramVersionReference() {
    }

    public TestProgramVersionReference(String programName, String parametersHash, Long programVersion, Date insertTime) {
        this.programName = programName;
        this.parametersHash = parametersHash;
        this.programVersion = programVersion;
        this.insertTime = insertTime;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getParametersHash() {
        return parametersHash;
    }

    public void setParametersHash(String parametersHash) {
        this.parametersHash = parametersHash;
    }

    public Long getProgramVersion() {
        return programVersion;
    }

    public void setProgramVersion(Long programVersion) {
        this.programVersion = programVersion;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    @Override
    public String toString() {
        return "TestProgramVersionReference{" +
                "programName='" + programName + '\'' +
                ", parametersHash='" + parametersHash + '\'' +
                ", programVersion='" + programVersion + '\'' +
                ", insertTime=" + insertTime +
                ", id=" + id +
                '}';
    }
}








