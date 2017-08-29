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
    //todo decide how to handle drafts/production rules
    boolean isDraft();
    boolean inProduction();
    boolean isValidForLPRReports();
    boolean isValidForDUSASAbroadReports();
    boolean isValidForDUSASSpecialityReports();
    Long errorNumber();
    String errorText();
    LprRuleGroup ruleGroup();
    LprErrorType errorType();
}
