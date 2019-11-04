package src.main.java.com.hjchanna.talend.validator;

import src.main.java.com.hjchanna.talend.Constraints;
import src.main.java.com.hjchanna.talend.util.FileUtil;
import src.main.java.com.hjchanna.talend.dto.ValidationRequest;
import src.main.java.com.hjchanna.talend.dto.ValidationResponse;
import src.main.java.com.hjchanna.talend.util.ValidationUtil;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;

/**
 * @author ChannaJ
 */
public class FileNameValidator implements TalendValidator {
    private static final Logger LOGGER = Logger.getLogger(FileNameValidator.class);

    private final ValidationRequest validationRequest;

    public FileNameValidator(ValidationRequest validationRequest) {
        this.validationRequest = validationRequest;
    }

    @Override
    public ValidationResponse validate(File root) {
        LOGGER.info("start validate for validation rule '" + validationRequest.getName() + "'");
        ValidationResponse validationResponse = new ValidationResponse(validationRequest);

        for (String fileLocation : validationRequest.getFileLocations()) {
            LOGGER.debug("validating directory " + fileLocation);
            File validationDir = new File(root, fileLocation);
            List<File> sourceFiles = FileUtil.listFiles(validationDir, validationRequest.getFilePattern(), validationRequest.getIncludeSubDirectories());

            for (File sourceFile : sourceFiles) {
                boolean matches = (sourceFile.getName().matches(validationRequest.getAssertionRegex()));

                LOGGER.debug("Validated file " + sourceFile.getName() + " against regex '" + validationRequest.getAssertionRegex() + "', matches: " + matches);

                String fileNameWithoutExtension = FileUtil.getFileRelativePathWithoutExtension(validationDir, sourceFile);

                if (!matches) {
                    //validation failed
                    validationResponse.addFeedback(
                            ValidationUtil.getValidationResponseLevel(validationRequest.getLevel()),
                            validationRequest.getName() + " is failed for object " + fileNameWithoutExtension
                    );
                } else {
                    //validation success
                    validationResponse.addFeedback(
                            Constraints.RESPONSE_SUCCESS,
                            validationRequest.getName() + " is success for object " + fileNameWithoutExtension
                    );
                }
            }
        }

        LOGGER.info("finished validation of '" + validationRequest.getName() + "', validation fail count: " + validationResponse.getValidationFeedbacks().size());

        return validationResponse;
    }
}
