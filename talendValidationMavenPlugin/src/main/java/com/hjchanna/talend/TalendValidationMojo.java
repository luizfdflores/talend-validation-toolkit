package src.main.java.com.hjchanna.talend;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.project.MavenProject;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized;
import org.testng.annotations.Parameters;

import src.main.java.com.hjchanna.talend.dto.ValidationResponse;

@Mojo(name = "validate-talend", defaultPhase = LifecyclePhase.VERIFY)
public class TalendValidationMojo extends AbstractMojo {

    @Parameter(readonly = true, defaultValue = "${project}")
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        File root = project.getModel().getProjectDirectory();

        System.out.println(root);

        getLog().info("--------------------------------------------------------");
        getLog().info("T A L E N D  V A L I D A T I O N  M A V E N  P L U G I N");
        getLog().info("--------------------------------------------------------");

        List<ValidationResponse> validationResponses = TalendValidationUtil.getInstance().validateTalendProject(root);

        boolean breakTheBuild = false;
        for (ValidationResponse validationResponse : validationResponses) {
            getLog().info("--------------------------------------------------------");
            getLog().info("Talend standard validation failed for '" + validationResponse.getValidationRequest().getName() + "'");
            getLog().info("(Rule: " + validationResponse.getValidationRequest().getDescription() + ")");
            getLog().info("--------------------------------------------------------");

            for (ValidationResponse.ValidationFeedback feedback : validationResponse.getValidationFeedbacks()) {
                switch (feedback.getLevel()) {
                    case Constraints.RESPONSE_SUCCESS:
                        getLog().info(feedback.getMessage());
                        break;
                    case Constraints.RESPONSE_WARN:
                        getLog().warn(feedback.getMessage());
                        break;
                    case Constraints.RESPONSE_ERROR:
                        getLog().error(feedback.getMessage());

                        breakTheBuild = true;
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }

        if (breakTheBuild) {
            throw new MojoExecutionException("Talend standard validation failed.");
        }
    }
}
