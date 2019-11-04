package src.main.java.com.hjchanna.talend.validator;

import src.main.java.com.hjchanna.talend.dto.ValidationResponse;

import java.io.File;

/**
 * @author ChannaJ
 */
public interface TalendValidator {

    ValidationResponse validate(File root);
}
