/*******************************************************************************
 * Copyright (c) 2010 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.views.tree.commands;

import org.eclipse.jface.viewers.StructuredSelection;

import uk.ac.bolton.archimate.editor.model.commands.NonNotifyingCompoundCommand;
import uk.ac.bolton.archimate.editor.ui.services.UIRequestManager;
import uk.ac.bolton.archimate.editor.views.tree.TreeSelectionRequest;

/**
 * This Compound Command stores an element so that when elements are deleted the tree node can be re-selected.
 * It turns off refresh in the Tree, saving on slow redraws.
 * 
 * @author Phillip Beauvoir
 */
public class DeleteElementsCompoundCommand extends NonNotifyingCompoundCommand {

    // The object to select in the tree after the deletion
    private Object fObjectToSelect;

    /**
     * @param newObjectToSelect The new object to select in the tree after the deletion
     */
    public DeleteElementsCompoundCommand(Object newObjectToSelect) {
        fObjectToSelect = newObjectToSelect;
    }

    @Override
    public String getLabel() {
        return getCommands().size() > 1 ? "Delete Elements" : super.getLabel();
    }

    @Override
    public void execute() {
        super.execute();

        // Select object
        if(fObjectToSelect != null) {
            UIRequestManager.INSTANCE.fireRequest(new TreeSelectionRequest(this, new StructuredSelection(fObjectToSelect), true));
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        fObjectToSelect = null;
    }
}