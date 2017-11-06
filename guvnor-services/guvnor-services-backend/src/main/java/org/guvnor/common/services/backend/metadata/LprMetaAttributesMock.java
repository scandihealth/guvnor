package org.guvnor.common.services.backend.metadata;

import org.guvnor.common.services.backend.metadata.attribute.LprMetaAttributes;
import org.guvnor.common.services.shared.metadata.model.LprErrorType;
import org.guvnor.common.services.shared.metadata.model.LprRuleGroup;
import org.guvnor.common.services.shared.metadata.model.LprRuleType;
import org.uberfire.java.nio.base.FileTimeImpl;
import org.uberfire.java.nio.file.attribute.FileTime;

/**
 * Created on 23-05-2017.
 */
public class LprMetaAttributesMock implements LprMetaAttributes, Cloneable {
    public Long reportReceivedFromDate = 100L;
    public Long reportReceivedToDate = 200L;
    public Long encounterStartFromDate = 300L;
    public Long encounterStartToDate = 400L;
    public Long encounterEndFromDate = 500L;
    public Long encounterEndToDate = 600L;
    public Long episodeOfCareStartFromDate = 100L;
    public Long episodeOfCareStartToDate = 100L;
    public Long productionDate = 700L;
    public Long archivedDate = 0L;
    public boolean hasProdVersion = true;
    public boolean isValidForLPRReports = true;
    public boolean isValidForDUSASAbroadReports = false;
    public boolean isValidForDUSASSpecialityReports = false;
    public boolean isValidForPrimarySectorReports = true;
    public Long errorNumber = 1L;
    public LprErrorType errorType = LprErrorType.ERROR;
    public LprRuleType ruleType = LprRuleType.REPORT_VALIDATION;
    public String errorText = "Vi tester LPR";
    public LprRuleGroup ruleGroup = LprRuleGroup.MOBST;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public LprRuleType ruleType() {
        return ruleType;
    }

    @Override
    public Long reportReceivedFromDate() {
        return reportReceivedFromDate;
    }

    @Override
    public Long reportReceivedToDate() {
        return reportReceivedToDate;
    }

    @Override
    public Long encounterStartFromDate() {
        return encounterStartFromDate;
    }

    @Override
    public Long encounterStartToDate() {
        return encounterStartToDate;
    }

    @Override
    public Long encounterEndFromDate() {
        return encounterEndFromDate;
    }

    @Override
    public Long encounterEndToDate() {
        return encounterEndToDate;
    }

    @Override
    public Long episodeOfCareStartFromDate() {
        return episodeOfCareStartFromDate;
    }

    @Override
    public Long episodeOfCareStartToDate() {
        return episodeOfCareStartToDate;
    }

    @Override
    public Long archivedDate() {
        return archivedDate;
    }

    @Override
    public Long productionDate() {
        return productionDate;
    }

    @Override
    public boolean hasProdVersion() {
        return hasProdVersion;
    }

    @Override
    public boolean isValidForLPRReports() {
        return isValidForLPRReports;
    }

    @Override
    public boolean isValidForDUSASAbroadReports() {
        return isValidForDUSASAbroadReports;
    }

    @Override
    public boolean isValidForDUSASSpecialityReports() {
        return isValidForDUSASSpecialityReports;
    }

    @Override
    public boolean isValidForPrimarySectorReports() {
        return isValidForPrimarySectorReports;
    }

    @Override
    public Long errorNumber() {
        return errorNumber;
    }

    @Override
    public String errorText() {
        return errorText;
    }

    @Override
    public LprRuleGroup ruleGroup() {
        return ruleGroup;
    }

    @Override
    public LprErrorType errorType() {
        return errorType;
    }

    @Override
    public FileTime lastModifiedTime() {
        return new FileTimeImpl();
    }

    @Override
    public FileTime lastAccessTime() {
        return new FileTimeImpl();
    }

    @Override
    public FileTime creationTime() {
        return new FileTimeImpl();
    }

    @Override
    public boolean isRegularFile() {
        return false;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isSymbolicLink() {
        return false;
    }

    @Override
    public boolean isOther() {
        return false;
    }

    @Override
    public long size() {
        return 0;
    }

    @Override
    public Object fileKey() {
        return new Object();
    }
}
