package org.guvnor.common.services.backend.metadata.attribute;

import org.guvnor.common.services.shared.metadata.model.LprErrorType;
import org.guvnor.common.services.shared.metadata.model.LprRuleGroup;
import org.guvnor.common.services.shared.metadata.model.LprRuleType;
import org.uberfire.java.nio.file.attribute.BasicFileAttributes;

/**
 * Created on 16-02-2017.
 */
public interface LprMetaAttributes extends BasicFileAttributes {
    LprRuleType ruleType();
    Long reportReceivedFromDate();
    Long reportReceivedToDate();
    Long encounterStartFromDate();
    Long encounterStartToDate();
    Long encounterEndFromDate();
    Long encounterEndToDate();
    Long episodeOfCareStartFromDate();
    Long episodeOfCareStartToDate();
    Long archivedDate();
    Long productionDate();
    boolean hasProdVersion();
    boolean isValidForLPRReports();
    boolean isValidForDUSASAbroadReports();
    boolean isValidForDUSASSpecialityReports();
    boolean isValidForPrimarySectorReports();
    Long errorNumber();
    String errorText();
    Integer warningTimeLimit();
    LprRuleGroup ruleGroup();
    LprErrorType errorType();
}
