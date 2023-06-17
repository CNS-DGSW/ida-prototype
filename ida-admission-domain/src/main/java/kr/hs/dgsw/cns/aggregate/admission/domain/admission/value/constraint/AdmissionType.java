package kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.constraint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AdmissionType {
    NONE,
    GENERAL,
    MEISTER,
    REGIONAL_PRIORITY,
    SOCIAL_INTEGRATION,
    SPECIAL_ADMISSION,
    VETERANS,
    // TODO: we must write about enum values '특례입학'
    ;

    @JsonCreator
    public static AdmissionType from(String value) {
        for (AdmissionType type : AdmissionType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }
}
