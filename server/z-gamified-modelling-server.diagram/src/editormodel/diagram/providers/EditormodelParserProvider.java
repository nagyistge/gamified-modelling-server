/*
 * 
 */
package editormodel.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

import editormodel.EditormodelPackage;
import editormodel.diagram.edit.parts.DraggableItemDescriptionEditPart;
import editormodel.diagram.edit.parts.LevelCaseDescriptionEditPart;
import editormodel.diagram.edit.parts.LevelDescriptionEditPart;
import editormodel.diagram.edit.parts.ObjectiveDescriptionEditPart;
import editormodel.diagram.edit.parts.StoryDescriptionEditPart;
import editormodel.diagram.edit.parts.SubStoryDescriptionEditPart;
import editormodel.diagram.parsers.MessageFormatParser;
import editormodel.diagram.part.EditormodelVisualIDRegistry;

/**
 * @generated
 */
public class EditormodelParserProvider extends AbstractProvider implements IParserProvider {

	/**
	* @generated
	*/
	private IParser storyDescription_5006Parser;

	/**
	* @generated
	*/
	private IParser getStoryDescription_5006Parser() {
		if (storyDescription_5006Parser == null) {
			EAttribute[] features = new EAttribute[] { EditormodelPackage.eINSTANCE.getStory_Description() };
			MessageFormatParser parser = new MessageFormatParser(features);
			storyDescription_5006Parser = parser;
		}
		return storyDescription_5006Parser;
	}

	/**
	* @generated
	*/
	private IParser subStoryDescription_5005Parser;

	/**
	* @generated
	*/
	private IParser getSubStoryDescription_5005Parser() {
		if (subStoryDescription_5005Parser == null) {
			EAttribute[] features = new EAttribute[] { EditormodelPackage.eINSTANCE.getSubStory_Description() };
			MessageFormatParser parser = new MessageFormatParser(features);
			subStoryDescription_5005Parser = parser;
		}
		return subStoryDescription_5005Parser;
	}

	/**
	* @generated
	*/
	private IParser levelDescription_5004Parser;

	/**
	* @generated
	*/
	private IParser getLevelDescription_5004Parser() {
		if (levelDescription_5004Parser == null) {
			EAttribute[] features = new EAttribute[] { EditormodelPackage.eINSTANCE.getLevel_Description() };
			MessageFormatParser parser = new MessageFormatParser(features);
			levelDescription_5004Parser = parser;
		}
		return levelDescription_5004Parser;
	}

	/**
	* @generated
	*/
	private IParser objectiveDescription_5001Parser;

	/**
	* @generated
	*/
	private IParser getObjectiveDescription_5001Parser() {
		if (objectiveDescription_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { EditormodelPackage.eINSTANCE.getObjective_Description() };
			MessageFormatParser parser = new MessageFormatParser(features);
			objectiveDescription_5001Parser = parser;
		}
		return objectiveDescription_5001Parser;
	}

	/**
	* @generated
	*/
	private IParser levelCaseDescription_5003Parser;

	/**
	* @generated
	*/
	private IParser getLevelCaseDescription_5003Parser() {
		if (levelCaseDescription_5003Parser == null) {
			EAttribute[] features = new EAttribute[] { EditormodelPackage.eINSTANCE.getLevelCase_Description() };
			MessageFormatParser parser = new MessageFormatParser(features);
			levelCaseDescription_5003Parser = parser;
		}
		return levelCaseDescription_5003Parser;
	}

	/**
	* @generated
	*/
	private IParser draggableItemDescription_5002Parser;

	/**
	* @generated
	*/
	private IParser getDraggableItemDescription_5002Parser() {
		if (draggableItemDescription_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { EditormodelPackage.eINSTANCE.getDraggableItem_Description() };
			MessageFormatParser parser = new MessageFormatParser(features);
			draggableItemDescription_5002Parser = parser;
		}
		return draggableItemDescription_5002Parser;
	}

	/**
	* @generated
	*/
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case StoryDescriptionEditPart.VISUAL_ID:
			return getStoryDescription_5006Parser();
		case SubStoryDescriptionEditPart.VISUAL_ID:
			return getSubStoryDescription_5005Parser();
		case LevelDescriptionEditPart.VISUAL_ID:
			return getLevelDescription_5004Parser();
		case ObjectiveDescriptionEditPart.VISUAL_ID:
			return getObjectiveDescription_5001Parser();
		case LevelCaseDescriptionEditPart.VISUAL_ID:
			return getLevelCaseDescription_5003Parser();
		case DraggableItemDescriptionEditPart.VISUAL_ID:
			return getDraggableItemDescription_5002Parser();
		}
		return null;
	}

	/**
	* Utility method that consults ParserService
	* @generated
	*/
	public static IParser getParser(IElementType type, EObject object, String parserHint) {
		return ParserService.getInstance().getParser(new HintAdapter(type, object, parserHint));
	}

	/**
	* @generated
	*/
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(EditormodelVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(EditormodelVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	* @generated
	*/
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (EditormodelElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	* @generated
	*/
	private static class HintAdapter extends ParserHintAdapter {

		/**
		* @generated
		*/
		private final IElementType elementType;

		/**
		* @generated
		*/
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		* @generated
		*/
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
