package org.york.gamified.modelling;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.evl.EvlModule;
import org.eclipse.epsilon.evl.IEvlModule;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.york.gamified.modelling.model.Model;
import org.york.gamified.modelling.model.Node;

import com.google.gson.Gson;

import gamifiedmodellingobjectmodel.GamifiedmodellingobjectmodelFactory;
import gamifiedmodellingobjectmodel.GamifiedmodellingobjectmodelPackage;
import gamifiedmodellingobjectmodel.ObjectModel;

/**
 * Servlet implementation class Validation
 */
@WebServlet("/Validation")
public class Validation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Validation() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected java.net.URI getFileURI(String fileName) throws URISyntaxException {
		String path = "";
		for (int i = 0; i < Epsilon.class.getName().split("\\.").length; i++) {
			path = path + "../";
		}
		path = path + fileName;
		java.net.URL url = Epsilon.class.getResource(path);
		java.net.URI binUri = url.toURI();
		java.net.URI uri = null;

		if (binUri.toString().indexOf("bin") > -1) {
			uri = new java.net.URI(binUri.toString().replaceAll("bin", "src"));
		} else {
			uri = binUri;
		}

		return uri;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			StringBuilder stringBuilder = new StringBuilder();
			String result;
			while ((result = request.getReader().readLine()) != null) {
				stringBuilder.append(result);
			}

			String json = stringBuilder.toString();

			Gson gson = new Gson();
			Model model = gson.fromJson(json, Model.class);

			GamifiedmodellingobjectmodelPackage myPackage = GamifiedmodellingobjectmodelPackage.eINSTANCE;
			GamifiedmodellingobjectmodelFactory factory = GamifiedmodellingobjectmodelFactory.eINSTANCE;
			ObjectModel objectModel = factory.createObjectModel();
			for (Node node : model.nodes) {
				gamifiedmodellingobjectmodel.Object object = factory.createObject();
				object.setIdentity(node.identity);
				object.setName(node.objectName);
				objectModel.getObjects().add(object);
			}

			// create resource set and resource
			ResourceSet resourceSet = new ResourceSetImpl();
			// Register XML resource factory
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi",
					new XMIResourceFactoryImpl());

			// save XMI of the model
			String path = Epsilon.class.getProtectionDomain().getCodeSource().getLocation().getPath()
					+ "../epsilon/models/ObjectModel.xmi";
//			URI uri =  URI.createFileURI(path);
//			File file = new File(uri.path());
//			if (file.exists()){
//				file.delete();
//			}
			Resource resource = resourceSet.createResource(URI.createFileURI(path));
			resource.getContents().add(objectModel);
			resource.save(null);

			// Load EVL module
			IEvlModule module = new EvlModule();
			String source = "epsilon/evl/Validation.evl";
			java.net.URI binUri = getFileURI(source);
			module.parse(binUri);

			// create in memory Emf Model and add the model to Validation EVL
			InMemoryEmfModel inMemoryEmfModel = new InMemoryEmfModel(resource);
			inMemoryEmfModel.setName("ObjectModel");
			module.getContext().getModelRepository().addModel(inMemoryEmfModel);

			//excute the module
			module.execute();
			
			//do the validation
			String validationResult = "";
			List<UnsatisfiedConstraint> unsatisfiedConstraints = module.getContext().getUnsatisfiedConstraints();
			if (unsatisfiedConstraints.size() > 0) {
				for (UnsatisfiedConstraint unsatisfied : unsatisfiedConstraints) {
					unsatisfied.getMessage();
				}
				validationResult = "false";
			} else {
				validationResult = "true";
			}

			module.getContext().getModelRepository().dispose();

			//returning the result
			if (validationResult != null && validationResult.length() > 0) {
				response.setContentType("application/json");
				response.getWriter().append(validationResult);
				response.getWriter().flush();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
