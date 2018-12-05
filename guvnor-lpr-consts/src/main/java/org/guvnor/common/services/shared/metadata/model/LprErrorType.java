package org.guvnor.common.services.shared.metadata.model;

import java.util.HashMap;
import java.util.Map;

//todo ttn consider if this enum should be declared in LPR3 and imported as maven dependency in drools-wb
public enum LprErrorType {
    //these matches values in LPR enum ErrorVO.Severity
    ERROR( "Fejl", "2" ),
    WARN( "Advarsel", "1" );

    private String id;
    private static final Map<String, LprErrorType> _idMap = new HashMap<String, LprErrorType>();
    private final String displayText;

    //initialize id map
    static {
        for ( LprErrorType errorType : LprErrorType.values() ) {
            _idMap.put( errorType.id, errorType );
        }
    }

    LprErrorType( String displayText, String id ) {
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
    public static LprErrorType getById( String id ) {
        return _idMap.get( id );
    }
}
