package org.craftyourmod.mineclipse.installer.pages;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.craftyourmod.mineclipse.installer.Installer;
import org.craftyourmod.mineclipse.installer.Installer.GreyButtonRenderer;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.mihalis.opal.obutton.OButton;

import com.richclientgui.toolbox.progressIndicator.ImageSequencer;
import com.richclientgui.toolbox.samples.images.SampleToolBoxImageRegistry;

import org.eclipse.swt.graphics.Image;

import static com.richclientgui.toolbox.samples.images.SampleToolBoxImageRegistry.getImage;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class InstallPage extends Composite {

	private Label lblJDK;
	private Installer installer;
	private Label icon_JDK;
	private ImageSequencer isJDK;
	private StackLayout sl_composite_4;
	private Composite composite_4;
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public InstallPage(Composite parent, int style, Installer instance) {
		super(parent, style);
		this.installer = instance;
		setLayout(new GridLayout(1, false));

		Label lblNowSetupWizard = new Label(this, SWT.NONE);
		lblNowSetupWizard.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER,
				true, false, 1, 1));
		lblNowSetupWizard
				.setText("Now Setup Wizard will install necessary files");

		ScrolledComposite scrolledComposite = new ScrolledComposite(this,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(51, 51, 51));
		composite.setBackgroundMode(SWT.INHERIT_FORCE);
		composite.setLayout(new FillLayout(SWT.VERTICAL));

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));

		composite_4 = new Composite(composite_1, SWT.NONE);
		sl_composite_4 = new StackLayout();
		sl_composite_4.marginWidth = 4;
		sl_composite_4.marginHeight = 4;
		composite_4.setLayout(sl_composite_4);
		GridData gd_composite_4 = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 2);
		gd_composite_4.widthHint = 40;
		gd_composite_4.heightHint = 40;
		composite_4.setLayoutData(gd_composite_4);

		icon_JDK = new Label(composite_4, SWT.NONE);
		icon_JDK.setImage(SWTResourceManager.getImage(InstallPage.class,
				"/icons/remain.gif"));

		isJDK = new ImageSequencer(
				composite_4,
				SWT.NONE,
				new Image[] {
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_1),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_2),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_3),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_4),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_5),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_6),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_7),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_8),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_9),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_10),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_11),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_12) },
				75, true);

		sl_composite_4.topControl = icon_JDK;
		Label lblJdkInstallation = new Label(composite_1, SWT.NONE);
		lblJdkInstallation.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblJdkInstallation.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblJdkInstallation.setText("JDK Installation");

		lblJDK = new Label(composite_1, SWT.NONE);
		lblJDK.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblJDK.setText("Waiting...");

		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(new GridLayout(2, false));

		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		GridData gd_composite_3 = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 2);
		gd_composite_3.heightHint = 40;
		gd_composite_3.widthHint = 40;
		composite_3.setLayoutData(gd_composite_3);
		composite_3.setLayout(new GridLayout(1, false));

		Label icon_Eclipse = new Label(composite_3, SWT.NONE);
		icon_Eclipse.setImage(SWTResourceManager.getImage(InstallPage.class,
				"/icons/remain.gif"));
		
		ImageSequencer imageSequencerEclipse = new ImageSequencer(
				composite_4,
				SWT.NONE,
				new Image[] {
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_1),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_2),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_3),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_4),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_5),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_6),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_7),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_8),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_9),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_10),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_11),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_12) },
				75, true);

		Label lblEclipseInstallation = new Label(composite_2, SWT.NONE);
		lblEclipseInstallation.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblEclipseInstallation.setFont(SWTResourceManager.getFont("Segoe UI",
				9, SWT.BOLD));
		lblEclipseInstallation.setText("Eclipse Installation");

		Label lblEclipse = new Label(composite_2, SWT.NONE);
		lblEclipse.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblEclipse.setText("Waiting...");

		Composite composite_5 = new Composite(composite, SWT.NONE);
		composite_5.setLayout(new GridLayout(2, false));

		Composite composite_6 = new Composite(composite_5, SWT.NONE);
		GridData gd_composite_6 = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 2);
		gd_composite_6.heightHint = 40;
		composite_6.setLayoutData(gd_composite_6);
		composite_6.setLayout(new GridLayout(1, false));

		Label icon_MC = new Label(composite_6, SWT.NONE);
		icon_MC.setImage(SWTResourceManager.getImage(InstallPage.class,
				"/icons/remain.gif"));
		
		ImageSequencer imageSequencerMC = new ImageSequencer(
				composite_4,
				SWT.NONE,
				new Image[] {
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_1),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_2),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_3),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_4),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_5),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_6),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_7),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_8),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_9),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_10),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_11),
						getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_12) },
				75, true);

		Label lblMineclipseInstallation = new Label(composite_5, SWT.NONE);
		lblMineclipseInstallation.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblMineclipseInstallation.setText("MineClipse Installation");
		lblMineclipseInstallation.setFont(SWTResourceManager.getFont(
				"Segoe UI", 9, SWT.BOLD));

		Label labelMineClipse = new Label(composite_5, SWT.NONE);
		labelMineClipse.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		labelMineClipse.setText("Waiting...");
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT,
				SWT.DEFAULT));

		OButton button = new OButton(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				installer.setNextEnabled(false);
				if(!installJDK().isOK())
					return;
				installer.setNextEnabled(true);
			}
		});
		button.setText("Run Now");
		button.setButtonRenderer(Installer.GreyButtonRenderer.getInstance());
	}
	private void updateJDKText(final String text) {
		getDisplay().asyncExec(new Runnable() {
			public void run() {
				lblJDK.setText(text);
			}
		});
	}
	private IStatus installJDK() {
		if(Boolean.parseBoolean(installer.config.get("JDKInstalled"))){
			icon_JDK.setImage(SWTResourceManager.getImage(InstallPage.class,
					"/icons/no.gif"));
			updateJDKText("JDK is already installed");
			return Status.OK_STATUS;
		} 
		showJDKPI();
		String sUrl = "";
		String arch = installer.config.get("OSArch");
		CookieManager cm = new CookieManager();
		try {
			cm.getCookieStore().add(new URI("http://oracle.com"), new HttpCookie("gpw_e24", ""));
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return new Status(Status.ERROR, null, "Canno't add cookie", e);
		}
		if(arch.equals("x86")) {;
				sUrl ="http://download.oracle.com/otn-pub/java/jdk/7u25-b17/jdk-7u25-windows-i586.exe";
				CookieHandler.setDefault(cm);
				try { 
					URL url = new URL(sUrl);
					ReadableByteChannel rbc = Channels.newChannel(url.openStream());
					FileOutputStream fos = new FileOutputStream("C:/Users/françois/Documents/Invité/test.tmp");

					updateJDKText("Downloading "+url.getContent());
					fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
					fos.close();
				} catch (MalformedURLException e) {
					e.printStackTrace();
									
				} catch (IOException e) {
					e.printStackTrace();
									
				}
		}
		System.out.println("Done");
		return Status.OK_STATUS;

	}
	private void showJDKPI() {
		sl_composite_4.topControl = isJDK;
		composite_4.layout();
		isJDK.startSequence();
		
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
