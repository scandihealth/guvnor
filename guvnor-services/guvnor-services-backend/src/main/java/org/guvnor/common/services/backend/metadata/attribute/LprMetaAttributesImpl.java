package org.guvnor.common.services.backend.metadata.attribute;

import org.guvnor.common.services.shared.metadata.model.LprErrorType;
import org.guvnor.common.services.shared.metadata.model.LprRuleType;
import org.guvnor.common.services.shared.metadata.model.Metadata;
import org.uberfire.java.nio.file.attribute.BasicFileAttributes;
import org.uberfire.java.nio.file.attribute.FileTime;

public class LprMetaAttributesImpl implements LprMetaAttributes {
    private LprRuleType.RuleType type = LprRuleType.RuleType.NORMAL;
    private Long ruleValidFromDate = 0L; //by default the rule will be active for all reports
    private Long ruleValidToDate = Long.MAX_VALUE; //by default the rule will be active for all reports
    private boolean isDraft = false;
    private boolean inProduction = false;
    private Long errorNumber = 0L;
    private String errorText = "";
    private String ruleGroup = "";
    private LprErrorType errorType = LprErrorType.NONE;
    private final BasicFileAttributes fileAttrs;

    public LprMetaAttributesImpl(final BasicFileAttributes fileAttrs) {
        this.fileAttrs = fileAttrs;
    }

    public LprMetaAttributesImpl(final Metadata metadata) {
        this.type = metadata.getLprRuleType();
        this.ruleValidFromDate = metadata.getRuleValidFromDate();
        this.ruleValidToDate = metadata.getRuleValidToDate();
        this.isDraft = metadata.isDraft();
        this.inProduction = metadata.isInProduction();
        this.errorNumber = metadata.getErrorNumber();
        this.errorText = metadata.getErrorText();
        this.ruleGroup = metadata.getRuleGroup();
        this.errorType = metadata.getErrorType();


        this.fileAttrs = new BasicFileAttributes() {
            @Override
            public FileTime lastModifiedTime() { return null; }

            @Override
            public FileTime lastAccessTime() {
                return null;
            }

            @Override
            public FileTime creationTime() {
                return null;
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
                return null;
            }
        };
    }

    public LprRuleType.RuleType getType() {
        return type;
    }

    public void setType(LprRuleType.RuleType type) {
        this.type = type;
    }

    public Long getRuleValidFromDate() {
        return ruleValidFromDate;
    }

    public void setRuleValidFromDate( Long ruleValidFromDate ) {
        this.ruleValidFromDate = ruleValidFromDate;
    }

    public Long getRuleValidToDate() {
        return ruleValidToDate;
    }

    public void setRuleValidToDate( Long ruleValidToDate ) {
        this.ruleValidToDate = ruleValidToDate;
    }

    public Long errorNumber() {return getErrorNumber(); };
    public String errorText() {return getErrorText(); };
    public String ruleGroup() {return getRuleGroup(); };
    public LprErrorType errorType() { return getErrorType();};

    @Override
    public LprRuleType.RuleType Type() {
        return getType();
    }

    @Override
    public Long ruleValidFromDate() {
        return getRuleValidFromDate();
    }

    @Override
    public Long ruleValidToDate() {
        return getRuleValidToDate();
    }

    @Override
    public boolean isDraft() {
        return isDraft;
    }

    @Override
    public boolean inProduction() {
        return isInProduction();
    }

    public void setDraft(boolean draft) {
        isDraft = draft;
    }

    public boolean isInProduction() {
        return inProduction;
    }

    public void setInProduction(boolean inProduction) {
        this.inProduction = inProduction;
    }

    @Override
    public FileTime lastModifiedTime() {
        return fileAttrs.lastModifiedTime();
    }

    @Override
    public FileTime lastAccessTime() {
        return fileAttrs.lastAccessTime();
    }

    @Override
    public FileTime creationTime() {
        return fileAttrs.creationTime();
    }

    @Override
    public boolean isRegularFile() {
        return fileAttrs.isRegularFile();
    }

    @Override
    public boolean isDirectory() {
        return fileAttrs.isDirectory();
    }

    @Override
    public boolean isSymbolicLink() {
        return fileAttrs.isSymbolicLink();
    }

    @Override
    public boolean isOther() {
        return fileAttrs.isOther();
    }

    @Override
    public long size() {
        return fileAttrs.size();
    }

    @Override
    public Object fileKey() {
        return fileAttrs.fileKey();
    }

    public Long getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(Long errorNumber) {
        this.errorNumber = errorNumber;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public String getRuleGroup() {
        return ruleGroup;
    }

    public void setRuleGroup(String ruleGroup) {
        this.ruleGroup = ruleGroup;
    }

    public LprErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(LprErrorType errorType) {
        this.errorType = errorType;
    }
}
