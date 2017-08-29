package org.guvnor.common.services.shared.metadata.model;

import java.util.HashMap;
import java.util.Map;

//todo ttn consider if this enum should be declared in LPR3 and imported as maven dependency in drools-wb
public enum LprRuleType {
    NOT_LPR("0"), //used to filter out all non-lpr related Drools-WB resources
    REPORT_VALIDATION("1");
    //VALIDATION_EXEMPTION("2");
    //todo if exemptions are stored in Drools-WB they should be added as rule type

    private String id;
    private static final Map<String, LprRuleType> _idMap = new HashMap<String, LprRuleType>();

    //initialize id map
    static {
        for ( LprRuleType ruleType : LprRuleType.values() ) {
            _idMap.put( ruleType.id, ruleType );
        }
    }

    LprRuleType( String id ) {
        this.id = id;
    }

    /**
     * The Id is used to make the enum searchable using LuceneSearchIndex with a WildcardQuery (the query works only with numbers as strings).
     * When an enum is saved, its id number is saved in the guvnor 'dot' xml file as a string, instead of the enum value itself.
     */
    public String getId() {
        return id;
    }

    /**
     * @see #getId()
     */
    public static LprRuleType getById( String id ) {
        return _idMap.get( id );
    }
}
