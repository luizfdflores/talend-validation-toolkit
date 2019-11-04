package com.hjchanna.talend.gui;

/**
 *
 * @author hjchanna
 */
public class MainPanel extends javax.swing.JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlProjectInfo;
    private javax.swing.JPanel pnlProjects;
    // End of variables declaration//GEN-END:variables

    public MainPanel() {
        initComponents();

        initOthers();
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {
        ProjectsPanel projectsPanel = new ProjectsPanel() {
            @Override
            protected void onTalenProjectSelect(java.io.File dir) {
                setView(TalendProjectView.getInstance(dir));
            }

            @Override
            protected void onNonTalenProjectSelect() {
                setView(NonTalendProjectView.getInstance());
            }
        };

        this.pnlProjects.add(projectsPanel);
        setView(NonTalendProjectView.getInstance());
    }

    private void setView(javax.swing.JPanel panel) {
        pnlProjectInfo.removeAll();
        pnlProjectInfo.add(panel);

        pnlProjectInfo.revalidate();
        pnlProjectInfo.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JSplitPane jSplitPane1 = new javax.swing.JSplitPane();
        pnlProjects = new javax.swing.JPanel();
        pnlProjectInfo = new javax.swing.JPanel();

        jSplitPane1.setDividerLocation(350);

        pnlProjects.setLayout(new javax.swing.BoxLayout(pnlProjects, javax.swing.BoxLayout.X_AXIS));
        jSplitPane1.setLeftComponent(pnlProjects);

        pnlProjectInfo.setLayout(new javax.swing.BoxLayout(pnlProjectInfo, javax.swing.BoxLayout.LINE_AXIS));
        jSplitPane1.setRightComponent(pnlProjectInfo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
    }// </editor-fold>//GEN-END:initComponents
}
