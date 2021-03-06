/*
 * 
 */
package editormodel.diagram.providers;

import editormodel.diagram.part.EditormodelDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {

	protected ElementInitializers() {
		// use #getInstance to access cached instance
	}

	/**
	* @generated
	*/
	public static ElementInitializers getInstance() {
		ElementInitializers cached = EditormodelDiagramEditorPlugin.getInstance().getElementInitializers();
		if (cached == null) {
			EditormodelDiagramEditorPlugin.getInstance().setElementInitializers(cached = new ElementInitializers());
		}
		return cached;
	}
}
