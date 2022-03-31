package ru.invitro.adapter.esb;

import ru.invitro.armps.integration.esb.publisher.converter.entity.util.EsbStringAssigner;
import ru.invitro.armps.integration.esb.remote.contact.Contact;
import ru.invitro.armps.integration.esb.remote.esbTypes.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

public class EsbTypesConversionUtils {
    private static final DatatypeFactory factory;

    public EsbTypesConversionUtils() {
    }

    public static EsbDatetime convert(OffsetDateTime date) {
        EsbDatetime result = new EsbDatetime();
        if (date == null) {
            result.setIsNullValue(true);
            return result;
        } else {
            GregorianCalendar gDate = GregorianCalendar.from(date.toZonedDateTime());
            XMLGregorianCalendar gregorianCalendar = factory.newXMLGregorianCalendar(gDate);
            gregorianCalendar.setTimezone(-2147483648);
            result.setValue(gregorianCalendar);
            return result;
        }
    }

    /** @deprecated */
    @Deprecated
    public static EsbDatetime convert(Calendar date) {
        EsbDatetime result = new EsbDatetime();
        if (date == null) {
            result.setIsNullValue(true);
            return result;
        } else {
            GregorianCalendar gDate = new GregorianCalendar(date.get(1), date.get(2), date.get(5), date.get(11), date.get(12), date.get(13));
            gDate.setTimeZone(date.getTimeZone());
            result.setValue(factory.newXMLGregorianCalendar(gDate));
            return result;
        }
    }

    public static EsbDatetime convert(LocalDate date) {
        EsbDatetime result = new EsbDatetime();
        if (date == null) {
            result.setIsNullValue(true);
            return result;
        } else {
            GregorianCalendar gDate = GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
            XMLGregorianCalendar gregorianCalendar = factory.newXMLGregorianCalendar(gDate);
            gregorianCalendar.setTimezone(-2147483648);
            result.setValue(gregorianCalendar);
            return result;
        }
    }

    public static EsbDecimal convert(BigDecimal value) {
        EsbDecimal result = new EsbDecimal();
        if (value == null) {
            result.setIsNullValue(true);
            return result;
        } else {
            result.setValue(value);
            return result;
        }
    }

    public static EsbForeignKey convert(UUID id) {
        EsbForeignKey result = new EsbForeignKey();
        if (id == null) {
            result.setIsNullValue(true);
            return result;
        } else {
            result.setValue(id.toString());
            return result;
        }
    }

    public static EsbString convert(String value) {
        EsbString result = new EsbString();
        EsbStringAssigner.setEsbString(result, value);
        return result;
    }

    public static EsbBoolean convert(Boolean value) {
        EsbBoolean result = new EsbBoolean();
        if (value == null) {
            result.setIsNullValue(true);
            return result;
        } else {
            result.setValue(value);
            return result;
        }
    }

    public static EsbInteger convert(Integer value) {
        EsbInteger result = new EsbInteger();
        if (value == null) {
            result.setIsNullValue(true);
            return result;
        } else {
            result.setValue(value);
            return result;
        }
    }

    public static EsbDecimal convert(int value) {
        EsbDecimal result = new EsbDecimal();
        result.setValue(BigDecimal.valueOf((long)value));
        return result;
    }

    public static Contact.PatientID createPatientID(String entity) {
        if (entity == null) {
            return null;
        } else {
            Contact.PatientID result = new Contact.PatientID();
            result.setValue(entity);
            return result;
        }
    }

    public static EsbPicklist convertPickList(String value) {
        EsbPicklist result = new EsbPicklist();
        if (value == null) {
            result.setIsNullValue(true);
            return result;
        } else {
            result.setValue(value);
            return result;
        }
    }

    static {
        try {
            factory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException var1) {
            throw new RuntimeException(var1);
        }
    }
}
