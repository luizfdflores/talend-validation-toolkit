package src.main.java.com.hjchanna.talend.gui;

import src.main.java.com.hjchanna.talend.model.FileTreeModel;
import src.main.java.com.hjchanna.talend.renderer.FileTreeRenderer;
import src.main.java.com.hjchanna.talend.util.TalendValidationToolUtils;
import java.io.File;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

/**
 *
 * @author hjchanna
 */
public abstract class ProjectsPanel extends javax.swing.JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scTreeFiles;
    private javax.swing.JTree treeFiles;
    // End of variables declaration//GEN-END:variables
    private boolean isNonTalendProjectSelected = false;

    public ProjectsPanel() {
        initComponents();

        initOthers();
    }

    private void initOthers() {
        treeFiles.setModel(new FileTreeModel());
        treeFiles.setCellRenderer(new FileTreeRenderer());

        treeFiles.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                if (e.getPath().getLastPathComponent() instanceof File) {
                    File selected = (File) e.getPath().getLastPathComponent();
                    if (TalendValidationToolUtils.isTalendProject(selected)) {
                        onTalenProjectSelect(selected);
                        isNonTalendProjectSelected = false;
                    } else {
                        if (!isNonTalendProjectSelected) {
                            onNonTalenProjectSelect();
                        }
                        isNonTalendProjectSelected = true;
                    }
                }
            }
        });
    }

    protected abstract void onTalenProjectSelect(File dir);

    protected abstract void onNonTalenProjectSelect();

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scTreeFiles = new javax.swing.JScrollPane();
        treeFiles = new javax.swing.JTree();

        treeFiles.setRootVisible(false);
        treeFiles.setRowHeight(19);
        treeFiles.setShowsRootHandles(true);
        scTreeFiles.setViewportView(treeFiles);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scTreeFiles, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scTreeFiles, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
}
