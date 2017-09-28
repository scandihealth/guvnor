package org.guvnor.common.services.shared.metadata.model;

import java.util.HashMap;
import java.util.Map;

//todo ttn consider if this enum should be declared in LPR3 and imported as maven dependency in drools-wb
public enum LprRuleGroup {
    NONE("" , "0" ),
    MOBST("MOBST", "1"),
    ULYKK("ULYKK", "2"),
    STEDF("STEDF", "3"),
    OKOMB("OKOMB", "4"),
    INDUD("INDUD", "5"),
    INDUD_SKSKO_MOBST("INDUD/SKSKO/MOBST", "6"),
    PSYKI("PSYKI", "7"),
    INDUD_BESOG("INDUD/BESØG", "8"),
    INDUD_SKSKO("INDUD/SKSKO", "9"),
    BESOG("BESØG", "10"),
    SKSKO("SKSKO", "11"),
    INDUD_PASSV("INDUD/PASSV", "12"),
    PATIENT("PATIENT", "13"),
    PASSV("PASSV", "14"),
    INDUD_VENTE("INDUD/VENTE", "15"),
    VENTE("VENTE", "16"),
    OPERA("OPERA", "17"),
    DUSAS("DUSAS", "18"),
    BOBST("BOBST", "19"),
    DUSAS_SPEC("DUSAS.SPEC", "20");

    private String id;
    private static final Map<String, LprRuleGroup> _idMap = new HashMap<String, LprRuleGroup>();
    private final String displayText;

    //initialize id map
    static {
        for ( LprRuleGroup ruleGroup : LprRuleGroup.values() ) {
            _idMap.put( ruleGroup.id, ruleGroup );
        }
    }

    LprRuleGroup( String displayText, String id ) {
        this.displayText = displayText;
        this.id = id;
    }

    /**
     * The Id is used to make the enum searchable using LuceneSearchIndex with a WildcardQuery (the query works only with numbers as strings).
     * When an enum is saved, its id number is saved in the guvnor 'dot' xml file as a string, instead of the enum value itself.
     */
    public String getId() {
        return id;
    }

    public String getDisplayText() {
        return displayText;
    }

    /**
     * @see #getId()
     */
    public static LprRuleGroup getById( String id ) {
        return _idMap.get( id );
    }
    }
