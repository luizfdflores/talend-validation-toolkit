package src.main.java.com.hjchanna.talend.model;

import src.main.java.com.hjchanna.talend.dto.ValidationResponse;
import java.util.List;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author ChannaJ
 */
public class ValidationTreeModel implements TreeModel {

    private List<ValidationResponse> validationResponses;

    public void setValidationResponses(List<ValidationResponse> validationResponses) {
        this.validationResponses = validationResponses;
    }

    @Override
    public Object getRoot() {
        return "ROOT";
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof String) {
            return this.validationResponses.get(index);
        }
        if (parent instanceof ValidationResponse) {
            return ((ValidationResponse) parent).getValidationFeedbacks().get(index);
        }

        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof String) {
            return this.validationResponses.size();
        }
        if (parent instanceof ValidationResponse) {
            return ((ValidationResponse) parent).getValidationFeedbacks().size();
        }

        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
        return (node instanceof ValidationResponse.ValidationFeedback);
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        //do nothing
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        //do nothing
        return 0;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        //do nothing
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        //do nothing
    }
}
