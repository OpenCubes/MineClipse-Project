import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This class displays a survey using a wizard
 */
public class Survey {
	/**
	 * Runs the application
	 */
	public void run() {
		Display display = new Display();

		// Create the parent shell for the dialog, but don't show it
		Shell shell = new Shell(display);

		// Create the dialog
		WizardDialog dlg = new WizardDialog(shell, new SurveyWizard());
		dlg.open();

		// Dispose the display
		display.dispose();
	}

	/**
	 * The application entry point
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(final String[] args) {
		new Survey().run();
	}
}

/**
 * This class shows a satisfaction survey
 */
class SurveyWizard extends Wizard {
	public SurveyWizard() {
		// Add the pages
		addPage(new ComplaintsPage());
		addPage(new MoreInformationPage());
		addPage(new ThanksPage());
	}

	/**
	 * Called when user clicks Finish
	 * 
	 * @return boolean
	 */
	@Override
	public boolean performFinish() {
		// Dismiss the wizard
		return true;
	}
}

/**
 * This class determines if the user has complaints. If not, it jumps to the
 * last page of the wizard
 */
class ComplaintsPage extends WizardPage {
	private Button yes;
	private Button no;

	/**
	 * ComplaintsPage constructor
	 */
	public ComplaintsPage() {
		super("Complaints");
	}

	/**
	 * Creates the page controls
	 */
	@Override
	public void createControl(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, true));

		new Label(composite, SWT.LEFT).setText("Do you have complaints?");
		Composite yesNo = new Composite(composite, SWT.NONE);
		yesNo.setLayout(new FillLayout(SWT.VERTICAL));

		yes = new Button(yesNo, SWT.RADIO);
		yes.setText("Yes");

		no = new Button(yesNo, SWT.RADIO);
		no.setText("No");

		setControl(composite);
	}

	@Override
	public IWizardPage getNextPage() {
		// If they have complaints, go to the normal next page
		if (yes.getSelection())
			return super.getNextPage();
		// No complaints? Short-circuit the rest of the pages
		return getWizard().getPage("Thanks");
	}
}

/**
 * This page gathers more information about the complaint
 */
class MoreInformationPage extends WizardPage {
	/**
	 * MoreInformationPage constructor
	 */
	public MoreInformationPage() {
		super("More Info");
	}

	/**
	 * Creates the controls for this page
	 */
	@Override
	public void createControl(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		new Label(composite, SWT.LEFT).setText("Please enter your complaints");
		Text text = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
		text.setLayoutData(new GridData(GridData.FILL_BOTH));

		setControl(composite);
	}
}

/**
 * This page thanks the user for taking the survey
 */
class ThanksPage extends WizardPage {
	/**
	 * ThanksPage constructor
	 */
	public ThanksPage() {
		super("Thanks");
	}

	/**
	 * Creates the controls for this page
	 */
	@Override
	public void createControl(final Composite parent) {
		Label label = new Label(parent, SWT.CENTER);
		label.setText("Thanks!");
		setControl(label);
	}
}
