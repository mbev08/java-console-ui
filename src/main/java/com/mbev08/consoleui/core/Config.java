package com.mbev08.consoleui.core;

import com.mbev08.consoleui.core.extensions.PropertyConverters;
import org.fusesource.jansi.Ansi.Color;

import java.util.Properties;
import java.io.InputStream;


/**
 * Manages the application's configuration, <b><i>(resources.config.properties)</i></b>
 */
public class Config {

    private static final Properties properties = new Properties();

    public static boolean debugModeEnabled, strictModeEnabled;
    public static Size appSizeDefault;
    public static Appearance appColorSchemeDefault;
    public static Position uiObjectPositionDefault;
    public static Size uiObjectSizeDefault;
    public static Spacing uiObjectPaddingDefault;
    public static Appearance uiObjectColorSchemeDefault;


    static {
        try (InputStream input = Config.class .getClassLoader()
                .getResourceAsStream("config.properties") ) {
            properties.load(input);
            importConfig();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load 'config.properties'!", e);
        }
    }

    private static void importConfig() {
        // EXECUTION.Behavior.modes
        {
            debugModeEnabled = PropertyConverters.convert(properties.getProperty("app.mode.debug"), Boolean.class);
            strictModeEnabled = PropertyConverters.convert(properties.getProperty("app.mode.strict"), Boolean.class);
        }

        // DEFAULTS.Application
        {
            int appDefaultWidth, appDefaultHeight;
            appDefaultWidth = PropertyConverters.convert(properties.getProperty("app.size.width"), Integer.class);
            appDefaultHeight = PropertyConverters.convert(properties.getProperty("app.size.height"), Integer.class);
            appSizeDefault = new Size(appDefaultWidth, appDefaultHeight);


            Color appDefaultBgColor, appDefaultFgColor;
            appDefaultBgColor = Color.valueOf(properties.getProperty("app.colorscheme.default.bg"));
            appDefaultFgColor = Color.valueOf(properties.getProperty("app.colorscheme.default.fg"));
            appColorSchemeDefault = new Appearance(appDefaultBgColor, appDefaultFgColor);
        }

        // DEFAULTS.UIObject
        {
            int uiObjectDefaultX, uiObjectDefaultY, uiObjectDefaultZ;
            uiObjectDefaultX = PropertyConverters.convert(properties.getProperty("UIObject.position.x"), Integer.class);
            uiObjectDefaultY = PropertyConverters.convert(properties.getProperty("UIObject.position.y"), Integer.class);
            uiObjectDefaultZ = PropertyConverters.convert(properties.getProperty("UIObject.position.z"), Integer.class);
            uiObjectPositionDefault = new Position(uiObjectDefaultX, uiObjectDefaultY, uiObjectDefaultZ);

            int uiObjectDefaultWidth, uiObjectDefaultHeight;
            uiObjectDefaultWidth = PropertyConverters.convert(properties.getProperty("UIObject.size.width"), Integer.class);
            uiObjectDefaultHeight = PropertyConverters.convert(properties.getProperty("UIObject.size.height"), Integer.class);
            uiObjectSizeDefault = new Size(uiObjectDefaultWidth, uiObjectDefaultHeight);

            int uiObjectDefaultPaddingTop, uiObjectDefaultPaddingBottom,
                    uiObjectDefaultPaddingLeft, uiObjectDefaultPaddingRight;
            uiObjectDefaultPaddingTop = PropertyConverters.convert(properties.getProperty("UIObject.padding.top"), Integer.class);
            uiObjectDefaultPaddingBottom = PropertyConverters.convert(properties.getProperty("UIObject.padding.bottom"), Integer.class);
            uiObjectDefaultPaddingLeft = PropertyConverters.convert(properties.getProperty("UIObject.padding.left"), Integer.class);
            uiObjectDefaultPaddingRight = PropertyConverters.convert(properties.getProperty("UIObject.padding.right"), Integer.class);
            uiObjectPaddingDefault = new Spacing(
                    uiObjectDefaultPaddingTop, uiObjectDefaultPaddingBottom,
                    uiObjectDefaultPaddingLeft, uiObjectDefaultPaddingRight);

            //Color uiObjectDefaultBgColor, uiObjectDefaultFgColor;
            //uiObjectDefaultBgColor = Color.valueOf(properties.getProperty("UIObject.colorscheme.default.bg"));
            //uiObjectDefaultFgColor = Color.valueOf(properties.getProperty("UIObject.colorscheme.default.fg"));
            //uiObjectColorSchemeDefault = new Appearance(uiObjectDefaultBgColor, uiObjectDefaultFgColor);

        }
    }

}
