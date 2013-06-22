/******************************************************************************
 * All Right Reserved. 
 * Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-5-20 16:38:39 by JACK
 * $Id$
 * 
 *****************************************************************************/

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

// The data model.
class ReservationData {
	Date arrivalDate;
	Date departureDate;
	int roomType;

	String customerName;
	String customerPhone;
	String customerEmail;
	String customerAddress;

	int creditCardType;
	String creditCardNumber;
	String creditCardExpiration;

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("* HOTEL ROOM RESERVATION DETAILS *\n");
		sb.append("Arrival date:\t" + arrivalDate.toString() + "\n");
		sb.append("Departure date:\t" + departureDate.toString() + "\n");
		sb.append("Room type:\t" + roomType + "\n");
		sb.append("Customer name:\t" + customerName + "\n");
		sb.append("Customer email:\t" + customerEmail + "\n");
		sb.append("Credit card no.:\t" + creditCardNumber + "\n");

		return sb.toString();
	}
}

/**
 * 
 */
class ReservationWizard extends Wizard {

	static final String DIALOG_SETTING_FILE = "userInfo.xml";

	static final String KEY_CUSTOMER_NAME = "customer-name";
	static final String KEY_CUSTOMER_EMAIL = "customer-email";
	static final String KEY_CUSTOMER_PHONE = "customer-phone";
	static final String KEY_CUSTOMER_ADDRESS = "customer-address";

	// the model object.
	ReservationData data = new ReservationData();

	public ReservationWizard() {
		setWindowTitle("Hotel room reservation wizard");
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(null,
				"icons/hotel.gif"));

		DialogSettings dialogSettings = new DialogSettings("userInfo");
		try {
			// loads existing settings if any.
			dialogSettings.load(DIALOG_SETTING_FILE);
		} catch (IOException e) {
			e.printStackTrace();
		}

		setDialogSettings(dialogSettings);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	@Override
	public void addPages() {
		addPage(new FrontPage());
		addPage(new CustomerInfoPage());
		addPage(new PaymentInfoPage());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		if (getDialogSettings() != null) {
			getDialogSettings().put(KEY_CUSTOMER_NAME, data.customerName);
			getDialogSettings().put(KEY_CUSTOMER_PHONE, data.customerPhone);
			getDialogSettings().put(KEY_CUSTOMER_EMAIL, data.customerEmail);
			getDialogSettings().put(KEY_CUSTOMER_ADDRESS, data.customerAddress);
			try {
				// Saves the dialog settings into the specified file.
				getDialogSettings().save(DIALOG_SETTING_FILE);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		try {
			// puts the data into a database ...
			getContainer().run(true, true, new IRunnableWithProgress() {
				@Override
				public void run(final IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Store data", 100);
					monitor.worked(40);

					// store data here ...
					System.out.println(data);

					Thread.sleep(2000);
					monitor.done();
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#performCancel()
	 */
	@Override
	public boolean performCancel() {
		boolean ans = MessageDialog.openConfirm(getShell(), "Confirmation",
				"Are you sure to cancel the task?");
		if (ans)
			return true;
		else
			return false;
	}
}

/******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-5-20 20:08:05 by JACK $Id$
 * 
 *****************************************************************************/

/**
 * 
 */
class PaymentInfoPage extends WizardPage {
	Combo comboCreditCardTypes;
	Text textCreditCardNumber;
	Text textCreditCardExpiration;

	public PaymentInfoPage() {
		super("PaymentInfo");
		setTitle("Payment information");
		setDescription("Please enter your credit card details");
		setPageComplete(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createControl(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(2, false));

		new Label(composite, SWT.NULL).setText("Credit card type: ");
		comboCreditCardTypes = new Combo(composite, SWT.READ_ONLY | SWT.BORDER);
		comboCreditCardTypes.add("American Express");
		comboCreditCardTypes.add("Master Card");
		comboCreditCardTypes.add("Visa");
		comboCreditCardTypes.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NULL).setText("Credit card number: ");
		textCreditCardNumber = new Text(composite, SWT.SINGLE | SWT.BORDER);
		textCreditCardNumber.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NULL).setText("Expiration (MM/YY)");
		textCreditCardExpiration = new Text(composite, SWT.SINGLE | SWT.BORDER);
		textCreditCardExpiration.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));

		comboCreditCardTypes.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				((ReservationWizard) getWizard()).data.creditCardType = comboCreditCardTypes
						.getSelectionIndex();
				if ((((ReservationWizard) getWizard()).data.creditCardNumber != null)
						&& (((ReservationWizard) getWizard()).data.creditCardExpiration != null))
					setPageComplete(true);
				else
					setPageComplete(false);
			}
		});

		textCreditCardNumber.addListener(SWT.Modify, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				((ReservationWizard) getWizard()).data.creditCardNumber = textCreditCardNumber
						.getText();

				if ((((ReservationWizard) getWizard()).data.creditCardNumber != null)
						&& (((ReservationWizard) getWizard()).data.creditCardExpiration != null))
					setPageComplete(true);
				else
					setPageComplete(false);
			}
		});

		textCreditCardExpiration.addListener(SWT.Modify, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				String text = textCreditCardExpiration.getText().trim();
				if ((text.length() == 5) && (text.charAt(2) == '/')) {
					((ReservationWizard) getWizard()).data.creditCardExpiration = text;
					setErrorMessage(null);
				} else {
					((ReservationWizard) getWizard()).data.creditCardExpiration = null;
					setErrorMessage("Invalid expiration date: " + text);
				}

				if ((((ReservationWizard) getWizard()).data.creditCardNumber != null)
						&& (((ReservationWizard) getWizard()).data.creditCardExpiration != null))
					setPageComplete(true);
				else
					setPageComplete(false);
			}
		});

		setControl(composite);
	}

}

/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-5-20 19:18:40 by JACK $Id$
 * 
 ******************************************************************************/

/**
 *  
 */
class CustomerInfoPage extends WizardPage {
	Text textName;
	Text textPhone;
	Text textEmail;
	Text textAddress;

	public CustomerInfoPage() {
		super("CustomerInfo");
		setTitle("Customer Information");

		setPageComplete(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createControl(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(2, false));

		new Label(composite, SWT.NULL).setText("Full name: ");
		textName = new Text(composite, SWT.SINGLE | SWT.BORDER);
		textName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NULL).setText("Phone Number: ");
		textPhone = new Text(composite, SWT.SINGLE | SWT.BORDER);
		textPhone.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NULL).setText("Email address: ");
		textEmail = new Text(composite, SWT.SINGLE | SWT.BORDER);
		textEmail.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NULL).setText("Address: ");
		textAddress = new Text(composite, SWT.MULTI | SWT.BORDER);
		textAddress.setText("\r\n\r\n\r\n");
		textAddress.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Listener listener = new Listener() {
			@Override
			public void handleEvent(final Event event) {
				if ((event.widget == null) || !(event.widget instanceof Text))
					return;

				String string = ((Text) event.widget).getText();

				if (event.widget == textName)
					((ReservationWizard) getWizard()).data.customerName = string;
				else if (event.widget == textPhone)
					((ReservationWizard) getWizard()).data.customerPhone = string;
				else if (event.widget == textEmail) {
					if (string.indexOf('@') < 0) {
						setErrorMessage("Invalid email address: " + string);
						((ReservationWizard) getWizard()).data.customerEmail = null;
					} else {
						setErrorMessage(null);
						((ReservationWizard) getWizard()).data.customerEmail = string;
					}
				} else if (event.widget == textAddress)
					((ReservationWizard) getWizard()).data.customerAddress = string;

				ReservationData data = ((ReservationWizard) getWizard()).data;
				if ((data.customerName != null) && (data.customerPhone != null)
						&& (data.customerEmail != null)
						&& (data.customerAddress != null))
					setPageComplete(true);
				else
					setPageComplete(false);
			}
		};

		textName.addListener(SWT.Modify, listener);
		textPhone.addListener(SWT.Modify, listener);
		textEmail.addListener(SWT.Modify, listener);
		textAddress.addListener(SWT.Modify, listener);

		if ((getDialogSettings() != null) && validDialogSettings()) {

			textName.setText(getDialogSettings().get(
					ReservationWizard.KEY_CUSTOMER_NAME));

			textPhone.setText(getDialogSettings().get(
					ReservationWizard.KEY_CUSTOMER_PHONE));

			textEmail.setText(getDialogSettings().get(
					ReservationWizard.KEY_CUSTOMER_EMAIL));

			textAddress.setText(getDialogSettings().get(
					ReservationWizard.KEY_CUSTOMER_ADDRESS));

		}

		setControl(composite);
	}

	private boolean validDialogSettings() {
		if ((getDialogSettings().get(ReservationWizard.KEY_CUSTOMER_NAME) == null)
				|| (getDialogSettings().get(
						ReservationWizard.KEY_CUSTOMER_ADDRESS) == null)
				|| (getDialogSettings().get(
						ReservationWizard.KEY_CUSTOMER_EMAIL) == null)
				|| (getDialogSettings().get(
						ReservationWizard.KEY_CUSTOMER_PHONE) == null))
			return false;
		return true;
	}

}

/******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-5-20 15:09:10 by JACK $Id$
 * 
 *****************************************************************************/

/**
 * 
 */
class FrontPage extends WizardPage {
	Combo comboRoomTypes;

	Combo comboArrivalYear;
	Combo comboArrivalMonth;
	Combo comboArrivalDay;
	Combo comboDepartureYear;
	Combo comboDepartureMonth;
	Combo comboDepartureDay;

	FrontPage() {
		super("FrontPage");
		setTitle("Your reservation information");
		setDescription("Select the type of room and your arrival date & departure date");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createControl(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout gridLayout = new GridLayout(2, false);
		composite.setLayout(gridLayout);

		new Label(composite, SWT.NULL).setText("Arrival date: ");

		Composite compositeArrival = new Composite(composite, SWT.NULL);
		compositeArrival.setLayout(new RowLayout());

		String[] months = new String[] { "Jan", "Feb", "Mar", "Apr", "May",
				"Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

		Calendar calendar = new GregorianCalendar(); // today.
		((ReservationWizard) getWizard()).data.arrivalDate = calendar.getTime();

		comboArrivalMonth = new Combo(compositeArrival, SWT.BORDER
				| SWT.READ_ONLY);
		for (String month : months)
			comboArrivalMonth.add(month);
		comboArrivalMonth.select(calendar.get(Calendar.MONTH));

		comboArrivalDay = new Combo(compositeArrival, SWT.BORDER
				| SWT.READ_ONLY);
		for (int i = 0; i < 31; i++)
			comboArrivalDay.add("" + (i + 1));
		comboArrivalDay.select(calendar.get(Calendar.DAY_OF_MONTH) - 1);

		comboArrivalYear = new Combo(compositeArrival, SWT.BORDER
				| SWT.READ_ONLY);
		for (int i = 2004; i < 2010; i++)
			comboArrivalYear.add("" + i);
		comboArrivalYear.select(calendar.get(Calendar.YEAR) - 2004);

		calendar.add(Calendar.DATE, 1); // tomorrow.
		((ReservationWizard) getWizard()).data.departureDate = calendar
				.getTime();

		new Label(composite, SWT.NULL).setText("Departure date: ");

		Composite compositeDeparture = new Composite(composite, SWT.NULL
				| SWT.READ_ONLY);
		compositeDeparture.setLayout(new RowLayout());

		comboDepartureMonth = new Combo(compositeDeparture, SWT.NULL
				| SWT.READ_ONLY);
		for (String month : months)
			comboDepartureMonth.add(month);
		comboDepartureMonth.select(calendar.get(Calendar.MONTH));

		comboDepartureDay = new Combo(compositeDeparture, SWT.NULL
				| SWT.READ_ONLY);
		for (int i = 0; i < 31; i++)
			comboDepartureDay.add("" + (i + 1));
		comboDepartureDay.select(calendar.get(Calendar.DAY_OF_MONTH) - 1);

		comboDepartureYear = new Combo(compositeDeparture, SWT.NULL
				| SWT.READ_ONLY);
		for (int i = 2004; i < 2010; i++)
			comboDepartureYear.add("" + i);
		comboDepartureYear.select(calendar.get(Calendar.YEAR) - 2004);

		// draws a line.
		Label line = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);

		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;
		line.setLayoutData(gridData);

		new Label(composite, SWT.NULL).setText("Room type: ");
		comboRoomTypes = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		comboRoomTypes.add("Standard room (rate: $198)");
		comboRoomTypes.add("Deluxe room (rate: $298)");
		comboRoomTypes.select(0);

		Listener selectionListener = new Listener() {
			@Override
			public void handleEvent(final Event event) {
				int arrivalDay = comboArrivalDay.getSelectionIndex() + 1;
				int arrivalMonth = comboArrivalMonth.getSelectionIndex();
				int arrivalYear = comboArrivalYear.getSelectionIndex() + 2004;

				int departureDay = comboDepartureDay.getSelectionIndex() + 1;
				int departureMonth = comboDepartureMonth.getSelectionIndex();
				int departureYear = comboDepartureYear.getSelectionIndex() + 2004;

				setDates(arrivalDay, arrivalMonth, arrivalYear, departureDay,
						departureMonth, departureYear);
			}
		};

		comboArrivalDay.addListener(SWT.Selection, selectionListener);
		comboArrivalMonth.addListener(SWT.Selection, selectionListener);
		comboArrivalYear.addListener(SWT.Selection, selectionListener);
		comboDepartureDay.addListener(SWT.Selection, selectionListener);
		comboDepartureMonth.addListener(SWT.Selection, selectionListener);
		comboDepartureYear.addListener(SWT.Selection, selectionListener);

		comboRoomTypes.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				((ReservationWizard) getWizard()).data.roomType = comboRoomTypes
						.getSelectionIndex();
			}
		});

		setControl(composite);

	}

	// validates the dates and update the model data object.
	private void setDates(final int arrivalDay, final int arrivalMonth,
			final int arrivalYear, final int departureDay,
			final int departureMonth, final int departureYear) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, arrivalDay);
		calendar.set(Calendar.MONTH, arrivalMonth);
		calendar.set(Calendar.YEAR, arrivalYear);

		Date arrivalDate = calendar.getTime();

		calendar.set(Calendar.DAY_OF_MONTH, departureDay);
		calendar.set(Calendar.MONTH, departureMonth);
		calendar.set(Calendar.YEAR, departureYear);

		Date departureDate = calendar.getTime();

		System.out.println(arrivalDate + " - " + departureDate);

		if (!arrivalDate.before(departureDate)) { // arrival date is before dep.
													// date.
			setErrorMessage("The arrival date is not before the departure date");
			setPageComplete(false);
		} else {
			setErrorMessage(null); // clear error message.
			setPageComplete(true);
			((ReservationWizard) getWizard()).data.arrivalDate = arrivalDate;
			((ReservationWizard) getWizard()).data.departureDate = departureDate;
		}
	}

}

/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-5-20 14:41:48 by JACK $Id$
 * 
 ******************************************************************************/

public class HotelReservation extends ApplicationWindow {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	protected Control createContents(final Composite parent) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText("Make a reservation");
		button.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				ReservationWizard wizard = new ReservationWizard();

				WizardDialog dialog = new WizardDialog(getShell(), wizard);
				dialog.setBlockOnOpen(true);
				int returnCode = dialog.open();
				if (returnCode == Dialog.OK)
					System.out.println(wizard.data);
				else
					System.out.println("Cancelled");
			}
		});
		return button;
	}

	/**
	 * @param parentShell
	 */
	public HotelReservation(final Shell parentShell) {
		super(parentShell);
	}

	public static void main(final String[] args) {
		HotelReservation reservation = new HotelReservation(null);
		reservation.setBlockOnOpen(true);
		reservation.open();
	}
}
