package org.craftyourmod.mineclipse.installer.pages;

import static com.richclientgui.toolbox.samples.images.SampleToolBoxImageRegistry.getImage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.craftyourmod.mineclipse.installer.Installer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.richclientgui.toolbox.progressIndicator.ImageSequencer;
import com.richclientgui.toolbox.samples.images.SampleToolBoxImageRegistry;

public class SystemTestPage extends Composite implements Runnable {
	private HashMap<String, String> config = new HashMap<>();
	private ImageSequencer imageSequencer;
	private Label lblPeaseWait;
	private Installer installer;
	protected boolean runned = false;
	private Label label_1;
	private Text text_1;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public SystemTestPage(Composite parent, int style, Installer instance) {
		super(parent, style);
		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				if (!runned) {

					installer.setNextEnabled(false);
					run();
					runned = true;
					installer.setNextEnabled(true);
				}
			}
		});
		installer = instance;
		setLayout(new GridLayout(3, false));

		Label lblWeNeedTo = new Label(this, SWT.NONE);
		lblWeNeedTo.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblWeNeedTo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 3, 1));
		lblWeNeedTo.setText("We need to check your system");

		imageSequencer = new ImageSequencer(this, SWT.NONE, new Image[] {
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
				getImage(SampleToolBoxImageRegistry.IMG_INDICATOR_D_12), }, 75,
				true);
		new Label(this, SWT.NONE);

		lblPeaseWait = new Label(this, SWT.NONE);
		lblPeaseWait.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false,
				false, 1, 2));
		lblPeaseWait
				.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPeaseWait.setText("Please wait...");

		label_1 = new Label(this, SWT.NONE);
		label_1.setVisible(false);
		label_1.setImage(SWTResourceManager.getImage(SystemTestPage.class,
				"/icons/done.gif"));
		new Label(this, SWT.NONE);

		text_1 = new Text(this, SWT.MULTI);
		text_1.setEditable(false);
		text_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	@Override
	public void run() {

		final Display display = Display.getCurrent();
		new Thread() {
			public void run() {

				updateStatus(display,
						"Please wait...\nDetecting Proxy Settings");
				detectProxy();
				updateStatus(display, "Please wait...\nDetecting System");
				detectSystem();
				updateStatus(display, "Please wait...\nDetecting JDK");
				detectJDK();
				stopProgress(display);
				updateStatus(display, "Done");
			}
		}.start();

	}

	private void detectJDK() {
		ProcessBuilder b = new ProcessBuilder("javac.exe", "-version");
		config.put("JDKInstalled", "true");
		try {
			b.start();
		} catch (IOException e1) {
			config.put("JDKInstalled", "false");
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}

	private void detectSystem() {
		config.put("OSName", System.getProperty("os.name"));
		config.put("OSArch", System.getProperty("os.arch"));
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}

	}

	private void detectProxy() {
		try {

			System.setProperty("java.net.useSystemProxies", "true");
			List l = ProxySelector.getDefault().select(
					new URI("http://mineclipse.olympe.in"));

			for (Iterator iter = l.iterator(); iter.hasNext();) {

				Proxy proxy = (Proxy) iter.next();

				InetSocketAddress addr = (InetSocketAddress) proxy.address();

				if (addr == null) {
					config.put("Proxy", "false");
					config.put("ProxyHost", "");
					config.put("ProxyPort", "");
				} else {

					config.put("ProxyHost", addr.getHostName());
					config.put("ProxyPort", "" + addr.getPort());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
							
		}

	}

	private void stopProgress(final Display display) {
		display.asyncExec(new Runnable() {
			public void run() {
				imageSequencer.stopSequence();
				label_1.setVisible(true);
				label_1.moveBelow(imageSequencer);
				imageSequencer.setVisible(false);
				imageSequencer.dispose();
				layout();
				for (String key : config.keySet()) {
					text_1.setText(text_1.getText() + key + "="
							+ config.get(key) + text_1.getLineDelimiter());
				}
				installer.config.putAll(config);
			}
		});
	}

	private void updateStatus(final Display display, final String text) {
		display.asyncExec(new Runnable() {
			public void run() {
				lblPeaseWait.setText(text);
			}
		});
	}
}
