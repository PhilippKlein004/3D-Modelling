package Controller;

import View.*;

/**
 * @author Philipp Klein (phi.klein17@gmail.com)
 * @version 1.0
 * @since 01.25.2024
 */

// NOTE: JFrames may have compatibility issues on Windows (ARM),
// potentially leading to malfunction in some cases.
public abstract class Controller {

    public static void main(String[] args) {
        new creatorGUI();
    }

}
