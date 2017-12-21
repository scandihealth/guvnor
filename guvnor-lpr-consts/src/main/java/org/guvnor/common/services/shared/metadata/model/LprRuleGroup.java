package org.guvnor.common.services.shared.metadata.model;

import java.util.HashMap;
import java.util.Map;

public enum LprRuleGroup {
    NONE("" , "0" ),
    BETALINGSOPLYSNING("Betalingsoplysning", "1"),
    BOPAEL("Bopæl", "2"),
    DIAGNOSE("Diagnose", "3"),
    FORLOEBSELEMENT("Forløbselement", "4"),
    FORLOEBSMARKOER("Forløbsmarkør", "5"),
    HENVISNING("Henvisning", "7"),
    KONTAKT("Kontakt", "8"),
    KONTAKTAARSAG("Kontaktårsag", "9"),
    OPHOLDSADRESSE("Opholdsadresse", "10"),
    PATIENT("Patient", "11"),
    PROCEDURE("Procedure", "12"),
    REFERENCE("Reference", "13"),
    RESULTAT("Resultat", "14"),
    RESULTATINDBERETNING("Resultatindberetning", "15");

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
