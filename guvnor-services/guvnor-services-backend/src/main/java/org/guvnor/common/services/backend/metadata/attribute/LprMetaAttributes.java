package org.guvnor.common.services.backend.metadata.attribute;

import org.guvnor.common.services.shared.metadata.model.LprErrorType;
import org.guvnor.common.services.shared.metadata.model.LprRuleType;
import org.uberfire.java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by prc on 16-02-2017.
 */
public interface LprMetaAttributes extends BasicFileAttributes {
    LprRuleType.RuleType Type();
    Long ruleValidFromDate();
    Long ruleValidToDate();
    boolean isDraft();
    boolean inProduction();
    Long errorNumber();
    String errorText();
    String ruleGroup();
    LprErrorType errorType();
}
