package src.main.java.com.hjchanna.talend;

import src.main.java.com.hjchanna.talend.dto.ValidationRequest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TalendValidationParserTest {

    private TalendValidationParser talendValidationParser;

//    @Test
    public void getValidationRequests() {
        //test getInstance is correct
        this.talendValidationParser = TalendValidationParser.getInstance();

        assertNotNull(this.talendValidationParser);

        //check inbuilt validation requests are properly decoded
        List<ValidationRequest> validationRequests = this.talendValidationParser.getValidationRequests();

        assertEquals(2, validationRequests.size());
    }
}