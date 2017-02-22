package org.guvnor.common.services.backend.metadata.attribute;

import org.guvnor.common.services.shared.metadata.model.LprRuleType;
import org.guvnor.common.services.shared.metadata.model.Metadata;
import org.uberfire.java.nio.file.attribute.BasicFileAttributes;
import org.uberfire.java.nio.file.attribute.FileTime;

import java.util.Date;

/**
 * Created by prc on 17-02-2017.
 */
public class LprMetaAttributesImpl implements LprMetaAttributes {
    private LprRuleType.RuleType type = LprRuleType.RuleType.NORMAL;
    private Long validFrom = 0L;

    private Long validTo = 0L;
    private boolean isDraft = false;
    private boolean inProduction = false;
    private final BasicFileAttributes fileAttrs;

    public LprMetaAttributesImpl(final BasicFileAttributes fileAttrs) {
        this.fileAttrs = fileAttrs;
    }

    public LprMetaAttributesImpl(final Metadata metadata) {
        this.type = metadata.getLprRuleType();
        this.validFrom = metadata.getValidFrom();
        this.validTo = metadata.getValidTo();
        this.isDraft = metadata.isDraft();
        this.inProduction = metadata.isInProduction();
        this.fileAttrs = new BasicFileAttributes() {
            @Override
            public FileTime lastModifiedTime() {
                return null;
            }

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

    public Long getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Long validFrom) {
        this.validFrom = validFrom;
    }

    public Long getValidTo() {
        return validTo;
    }

    public void setValidTo(Long validTo) {
        this.validTo = validTo;
    }

    @Override
    public LprRuleType.RuleType Type() {
        return getType();
    }

    @Override
    public Long ValidFrom() {
        return getValidFrom();
    }

    @Override
    public Long ValidTo() {
        return getValidTo();
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
}
