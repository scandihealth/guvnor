package org.guvnor.common.services.backend.metadata;

import org.guvnor.common.services.shared.metadata.model.LprErrorType;
import org.guvnor.common.services.shared.metadata.model.LprRuleType;
import org.uberfire.java.nio.base.FileTimeImpl;
import org.uberfire.java.nio.file.attribute.FileTime;

/**
 * Created by prc on 23-05-2017.
 */
public class LprMetaAttributesMock implements org.guvnor.common.services.backend.metadata.attribute.LprMetaAttributes {
    final public static String TEST_ERROR_TEXT = "Vi tester LPR";
    final public static String TEST_RULE_GROUP = "LPR Test rule group";


    @Override
    public LprRuleType.RuleType Type() {
        return LprRuleType.RuleType.EXEMPTION;
    }

    @Override
    public Long ruleValidFromDate() {
        return 100L;
    }

    @Override
    public Long ruleValidToDate() {
        return 200L;
    }

    @Override
    public boolean isDraft() {
        return true;
    }

    @Override
    public boolean inProduction() {
        return false;
    }

    @Override
    public Long errorNumber() {
        return 300L;
    }

    @Override
    public String errorText() {
        return TEST_ERROR_TEXT;
    }

    @Override
    public String ruleGroup() {
        return TEST_RULE_GROUP;
    }

    @Override
    public LprErrorType errorType() {
        return LprErrorType.ERROR;
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
