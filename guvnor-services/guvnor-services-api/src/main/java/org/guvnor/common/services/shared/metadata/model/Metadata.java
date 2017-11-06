/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.guvnor.common.services.shared.metadata.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.impl.LockInfo;
import org.uberfire.java.nio.base.version.VersionRecord;

/**
 *
 */
@Portable
public class Metadata {

    private Path path;
    private Path realPath;
    private LockInfo lockInfo;

    //git info
    private String checkinComment;
    private String lastContributor;
    private String creator;
    //git -> basic file attrs
    private Date lastModified;
    private Date dateCreated;

    //pure dcore
    private String subject;
    private String type;
    private String externalRelation;
    private String externalSource;
    private String description;

    //not dcore
    private List<String> tags = new ArrayList<String>();
    private List<DiscussionRecord> discussion = new ArrayList<DiscussionRecord>();
    private List<VersionRecord> version = new ArrayList<VersionRecord>();

    // lpr
    private LprRuleType ruleType;
    private Long archivedDate;
    private Long productionDate;
    private boolean hasProdVersion;
    private boolean isValidForLPRReports;
    private boolean isValidForDUSASAbroadReports;
    private boolean isValidForDUSASSpecialityReports;
    private boolean isValidForPrimarySectorReports;
    private Long errorNumber;
    private String errorText;
    private LprRuleGroup ruleGroup;
    private LprErrorType errorType;
    private Long reportReceivedFromDate;
    private Long reportReceivedToDate;
    private Long encounterStartFromDate;
    private Long encounterStartToDate;
    private Long encounterEndFromDate;
    private Long encounterEndToDate;
    private Long episodeOfCareStartFromDate;
    private Long episodeOfCareStartToDate;

    public Metadata() {

    }

    public Metadata( final Path path,
                     final Path realPath,
                     final String checkinComment,
                     final String lastContributor,
                     final String creator,
                     final Date lastModified,
                     final Date dateCreated,
                     final String subject,
                     final String type,
                     final String externalRelation,
                     final String externalSource,
                     final String description,
                     final List<String> tags,
                     final List<DiscussionRecord> discussion,
                     final List<VersionRecord> version,
                     final LockInfo lockInfo,
                     final LprRuleType ruleType,
                     final Long reportReceivedFromDate,
                     final Long reportReceivedToDate,
                     final Long encounterStartFromDate,
                     final Long encounterStartToDate,
                     final Long encounterEndFromDate,
                     final Long encounterEndToDate,
                     final Long episodeOfCareStartFromDate,
                     final Long episodeOfCareStartToDate,
                     final Long archivedDate,
                     final Long productionDate,
                     final boolean hasProdVersion,
                     final boolean isValidForLPRReports,
                     final boolean isValidForDUSASAbroadReports,
                     final boolean isValidForDUSASSpecialityReports,
                     final boolean isValidForPrimarySectorReports,
                     final Long errorNumber,
                     final String errorText,
                     final LprRuleGroup ruleGroup,
                     final LprErrorType errorType ) {
        this.path = path;
        this.realPath = realPath;
        this.checkinComment = checkinComment;
        this.lastContributor = lastContributor;
        this.creator = creator;
        this.lastModified = lastModified;
        this.dateCreated = dateCreated;
        this.subject = subject;
        this.type = type;
        this.externalRelation = externalRelation;
        this.externalSource = externalSource;
        this.description = description;
        this.tags = tags;
        this.discussion = discussion;
        this.version = version;
        this.lockInfo = lockInfo;
        //LPR
        this.reportReceivedFromDate = reportReceivedFromDate;
        this.reportReceivedToDate = reportReceivedToDate;
        this.encounterStartFromDate = encounterStartFromDate;
        this.encounterStartToDate = encounterStartToDate;
        this.encounterEndFromDate = encounterEndFromDate;
        this.encounterEndToDate = encounterEndToDate;
        this.episodeOfCareStartFromDate = episodeOfCareStartFromDate;
        this.episodeOfCareStartToDate = episodeOfCareStartToDate;
        this.ruleType = ruleType;
        this.archivedDate = archivedDate;
        this.productionDate = productionDate;
        this.hasProdVersion = hasProdVersion;
        this.isValidForLPRReports = isValidForLPRReports;
        this.isValidForDUSASAbroadReports = isValidForDUSASAbroadReports;
        this.isValidForDUSASSpecialityReports = isValidForDUSASSpecialityReports;
        this.isValidForPrimarySectorReports = isValidForPrimarySectorReports;
        this.errorNumber = errorNumber;
        this.errorText = errorText;
        this.ruleGroup = ruleGroup;
        this.errorType = errorType;
    }

    public Path getPath() {
        return path;
    }

    public Path getRealPath() {
        return realPath;
    }

    public String getCheckinComment() {
        return checkinComment;
    }

    public String getLastContributor() {
        return lastContributor;
    }

    public String getCreator() {
        return creator;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getSubject() {
        return subject;
    }

    public String getType() {
        return type;
    }

    public String getExternalRelation() {
        return externalRelation;
    }

    public String getExternalSource() {
        return externalSource;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<DiscussionRecord> getDiscussion() {
        return discussion;
    }

    public List<VersionRecord> getVersion() {
        return version;
    }

    public LockInfo getLockInfo() {
        return lockInfo;
    }

    public void setLockInfo( LockInfo lockInfo ) {
        this.lockInfo = lockInfo;
    }

    public void setSubject( final String subject ) {
        this.subject = subject;
    }

    public void setType( final String type ) {
        this.type = type;
    }

    public void setExternalRelation( final String externalRelation ) {
        this.externalRelation = externalRelation;
    }

    public void setExternalSource( final String externalSource ) {
        this.externalSource = externalSource;
    }

    public void addDiscussion( final DiscussionRecord discussionRecord ) {
        this.discussion.add( discussionRecord );
    }

    public void eraseDiscussion() {
        this.discussion.clear();
    }

    public void addTag( final String tag ) {
        tags.add( tag );
    }

    public void removeTag( final int idx ) {
        tags.remove( idx );
    }

    public void setDescription( final String description ) {
        this.description = description;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        Metadata metadata = ( Metadata ) o;

        if ( tags != null ? !tags.equals( metadata.tags ) : metadata.tags != null ) {
            return false;
        }
        if ( checkinComment != null ? !checkinComment.equals( metadata.checkinComment ) : metadata.checkinComment != null ) {
            return false;
        }
        if ( creator != null ? !creator.equals( metadata.creator ) : metadata.creator != null ) {
            return false;
        }
        if ( dateCreated != null ? !dateCreated.equals( metadata.dateCreated ) : metadata.dateCreated != null ) {
            return false;
        }
        if ( description != null ? !description.equals( metadata.description ) : metadata.description != null ) {
            return false;
        }
        if ( discussion != null ? !discussion.equals( metadata.discussion ) : metadata.discussion != null ) {
            return false;
        }
        if ( externalRelation != null ? !externalRelation.equals( metadata.externalRelation ) : metadata.externalRelation != null ) {
            return false;
        }
        if ( externalSource != null ? !externalSource.equals( metadata.externalSource ) : metadata.externalSource != null ) {
            return false;
        }
        if ( lastContributor != null ? !lastContributor.equals( metadata.lastContributor ) : metadata.lastContributor != null ) {
            return false;
        }
        if ( lastModified != null ? !lastModified.equals( metadata.lastModified ) : metadata.lastModified != null ) {
            return false;
        }
        if ( path != null ? !path.equals( metadata.path ) : metadata.path != null ) {
            return false;
        }
        if ( realPath != null ? !realPath.equals( metadata.realPath ) : metadata.realPath != null ) {
            return false;
        }
        if ( subject != null ? !subject.equals( metadata.subject ) : metadata.subject != null ) {
            return false;
        }
        if ( type != null ? !type.equals( metadata.type ) : metadata.type != null ) {
            return false;
        }
        if ( version != null ? !version.equals( metadata.version ) : metadata.version != null ) {
            return false;
        }
        if ( lockInfo != null ? !lockInfo.equals( metadata.lockInfo ) : metadata.lockInfo != null ) {
            return false;
        }
        //lpr metadata //todo remember to add all LPR meta data here
        if ( errorNumber != null ? !errorNumber.equals( metadata.errorNumber ) : metadata.errorNumber != null ) {
            return false;
        }
        if ( errorText != null ? !errorText.equals( metadata.errorText ) : metadata.errorText != null ) {
            return false;
        }
        if ( errorType != null ? !errorType.equals( metadata.errorType ) : metadata.errorType != null ) {
            return false;
        }
        if ( reportReceivedFromDate != null ? !reportReceivedFromDate.equals( metadata.reportReceivedFromDate ) : metadata.reportReceivedFromDate != null ) {
            return false;
        }
        if ( reportReceivedToDate != null ? !reportReceivedToDate.equals( metadata.reportReceivedToDate ) : metadata.reportReceivedToDate != null ) {
            return false;
        }
        if ( encounterStartFromDate != null ? !encounterStartFromDate.equals( metadata.encounterStartFromDate ) : metadata.encounterStartFromDate != null ) {
            return false;
        }
        if ( encounterStartToDate != null ? !encounterStartToDate.equals( metadata.encounterStartToDate ) : metadata.encounterStartToDate != null ) {
            return false;
        }
        if ( encounterEndFromDate != null ? !encounterEndFromDate.equals( metadata.encounterEndFromDate ) : metadata.encounterEndFromDate != null ) {
            return false;
        }
        if ( encounterEndToDate != null ? !encounterEndToDate.equals( metadata.encounterEndToDate ) : metadata.encounterEndToDate != null ) {
            return false;
        }
        if ( episodeOfCareStartFromDate != null ? !episodeOfCareStartFromDate.equals( metadata.episodeOfCareStartFromDate ) : metadata.episodeOfCareStartFromDate != null ) {
            return false;
        }
        if ( episodeOfCareStartToDate != null ? !episodeOfCareStartToDate.equals( metadata.episodeOfCareStartToDate ) : metadata.episodeOfCareStartToDate != null ) {
            return false;
        }
        if ( productionDate != null ? !productionDate.equals( metadata.productionDate ) : metadata.productionDate != null ) {
            return false;
        }
        if ( archivedDate != null ? !archivedDate.equals( metadata.archivedDate ) : metadata.archivedDate != null ) {
            return false;
        }
        if ( hasProdVersion != metadata.hasProdVersion ) {
            return false;
        }
        if ( isValidForLPRReports != metadata.isValidForLPRReports ) {
            return false;
        }
        if ( isValidForDUSASAbroadReports != metadata.isValidForDUSASAbroadReports ) {
            return false;
        }
        if ( isValidForDUSASSpecialityReports != metadata.isValidForDUSASSpecialityReports ) {
            return false;
        }
        if ( isValidForPrimarySectorReports != metadata.isValidForPrimarySectorReports ) {
            return false;
        }
        if ( ruleGroup != null ? !ruleGroup.equals( metadata.ruleGroup ) : metadata.ruleGroup != null ) {
            return false;
        }
        if ( ruleType != null ? !ruleType.equals( metadata.ruleType ) : metadata.ruleType != null ) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = ~~result;
        result = 31 * result + (realPath != null ? realPath.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (checkinComment != null ? checkinComment.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (lastContributor != null ? lastContributor.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (externalRelation != null ? externalRelation.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (externalSource != null ? externalSource.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (discussion != null ? discussion.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = ~~result;
        //lpr meta data //todo remember to add all LPR meta data here
        result = 31 * result + (errorNumber != null ? errorNumber.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (errorText != null ? errorText.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (errorType != null ? errorType.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (reportReceivedFromDate != null ? reportReceivedFromDate.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (reportReceivedToDate != null ? reportReceivedToDate.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (encounterStartFromDate != null ? encounterStartFromDate.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (encounterStartToDate != null ? encounterStartToDate.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (encounterEndFromDate != null ? encounterEndFromDate.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (encounterEndToDate != null ? encounterEndToDate.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (episodeOfCareStartFromDate != null ? episodeOfCareStartFromDate.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (episodeOfCareStartToDate != null ? episodeOfCareStartToDate.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (productionDate != null ? productionDate.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (archivedDate != null ? archivedDate.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (hasProdVersion ? 1 : 0);
        result = ~~result;
        result = 31 * result + (isValidForLPRReports ? 1 : 0);
        result = ~~result;
        result = 31 * result + (isValidForDUSASAbroadReports ? 1 : 0);
        result = ~~result;
        result = 31 * result + (isValidForDUSASSpecialityReports ? 1 : 0);
        result = ~~result;
        result = 31 * result + (isValidForPrimarySectorReports ? 1 : 0);
        result = ~~result;
        result = 31 * result + (ruleGroup != null ? ruleGroup.hashCode() : 0);
        result = ~~result;
        result = 31 * result + (ruleType != null ? ruleType.hashCode() : 0);
        result = ~~result;
        return result;
    }

    public LprRuleType getRuleType() {
        return ruleType;
    }

    public void setRuleType( LprRuleType ruleType ) {
        this.ruleType = ruleType;
    }

    public Long getReportReceivedFromDate() {
        return reportReceivedFromDate;
    }

    public void setReportReceivedFromDate( Long reportReceivedFromDate ) {
        this.reportReceivedFromDate = reportReceivedFromDate;
    }

    public Long getReportReceivedToDate() {
        return reportReceivedToDate;
    }

    public void setReportReceivedToDate( Long reportReceivedToDate ) {
        this.reportReceivedToDate = reportReceivedToDate;
    }

    public Long getArchivedDate() {
        return archivedDate;
    }

    public void setArchivedDate( Long archivedDate ) {
        this.archivedDate = archivedDate;
    }

    public Long getProductionDate() {
        return productionDate;
    }

    public void setProductionDate( Long productionDate ) {
        this.productionDate = productionDate;
    }

    public Long getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber( Long errorNumber ) {
        this.errorNumber = errorNumber;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText( String errorText ) {
        this.errorText = errorText;
    }

    public LprRuleGroup getRuleGroup() {
        return ruleGroup;
    }

    public void setRuleGroup( LprRuleGroup ruleGroup ) {
        this.ruleGroup = ruleGroup;
    }

    public LprErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType( LprErrorType errorType ) {
        this.errorType = errorType;
    }

    public Long getEncounterStartFromDate() {
        return encounterStartFromDate;
    }

    public void setEncounterStartFromDate( Long encounterStartFromDate ) {
        this.encounterStartFromDate = encounterStartFromDate;
    }

    public Long getEncounterStartToDate() {
        return encounterStartToDate;
    }

    public void setEncounterStartToDate( Long encounterStartToDate ) {
        this.encounterStartToDate = encounterStartToDate;
    }

    public boolean isValidForLPRReports() {
        return isValidForLPRReports;
    }

    public void setValidForLPRReports( boolean validForLPRReports ) {
        isValidForLPRReports = validForLPRReports;
    }

    public boolean isValidForDUSASAbroadReports() {
        return isValidForDUSASAbroadReports;
    }

    public void setValidForDUSASAbroadReports( boolean validForDUSASAbroadReports ) {
        isValidForDUSASAbroadReports = validForDUSASAbroadReports;
    }

    public boolean isValidForDUSASSpecialityReports() {
        return isValidForDUSASSpecialityReports;
    }

    public void setValidForDUSASSpecialityReports( boolean validForDUSASSpecialityReports ) {
        isValidForDUSASSpecialityReports = validForDUSASSpecialityReports;
    }

    public boolean isValidForPrimarySectorReports() {
        return isValidForPrimarySectorReports;
    }

    public void setValidForPrimarySectorReports( boolean validForPrimarySectorReports ) {
        isValidForPrimarySectorReports = validForPrimarySectorReports;
    }

    public Long getEncounterEndFromDate() {
        return encounterEndFromDate;
    }

    public void setEncounterEndFromDate( Long encounterEndFromDate ) {
        this.encounterEndFromDate = encounterEndFromDate;
    }

    public Long getEncounterEndToDate() {
        return encounterEndToDate;
    }

    public void setEncounterEndToDate( Long encounterEndToDate ) {
        this.encounterEndToDate = encounterEndToDate;
    }

    public Long getEpisodeOfCareStartFromDate() {
        return episodeOfCareStartFromDate;
    }

    public void setEpisodeOfCareStartFromDate( Long episodeOfCareStartFromDate ) {
        this.episodeOfCareStartFromDate = episodeOfCareStartFromDate;
    }

    public Long getEpisodeOfCareStartToDate() {
        return episodeOfCareStartToDate;
    }

    public void setEpisodeOfCareStartToDate( Long episodeOfCareStartToDate ) {
        this.episodeOfCareStartToDate = episodeOfCareStartToDate;
    }

    public boolean hasProdVersion() {
        return hasProdVersion;
    }

    public void setHasProdVersion( boolean hasProdVersion ) {
        this.hasProdVersion = hasProdVersion;
    }
}
