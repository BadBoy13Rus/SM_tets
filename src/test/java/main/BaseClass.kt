package main

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeSuite
import org.testng.annotations.Test
import java.net.URL
import java.util.concurrent.TimeUnit

class BaseClass {
    lateinit var driver: AppiumDriver<MobileElement>

    @BeforeSuite
    fun setupDriver() {
        val url = URL("http://127.0.0.1:4723/wd/hub")
        val caps = DesiredCapabilities()

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0")
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 4 API 30")
        caps.setCapability(MobileCapabilityType.NO_RESET, "true")
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "7200")
        caps.setCapability(MobileCapabilityType.APP, "/Users/Evgeny/Downloads/sportmaster-4.0.13.5605_dev_beta.apk")
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "ru.sportmaster.app.handh.dev")
        caps.setCapability(
            AndroidMobileCapabilityType.APP_ACTIVITY,
            "ru.sportmaster.app.presentation.start.StartActivity"
        )

        driver = AndroidDriver(url, caps)
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)

    }

    @AfterSuite
    fun end() {
        println("Тест завершен!")
        driver.quit()
    }

    @Test
    fun testOne() {
        TimeUnit.SECONDS.sleep(1)

        try {
            lateinit var element: MobileElement // создаем объект MobileElement
            element =
                driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageButton")) // производим поиск элемента по локатору id
            element.click() // клик по элементу
        } catch (e: org.openqa.selenium.NoSuchElementException) {
            println("Элемент не найден, продолжаем тест")
        }

        lateinit var element2: MobileElement // создаем объект MobileElement
        element2 =
            driver.findElement(MobileBy.id("ru.sportmaster.app.handh.dev:id/editTextPhone")) // производим поиск элемента по локатору id
        element2.sendKeys("9999999905") // ввод текста в поле

        lateinit var element3: MobileElement // создаем объект MobileElement
        element3 =
            driver.findElement(MobileBy.id("ru.sportmaster.app.handh.dev:id/buttonGetCode")) // производим поиск элемента по локатору id
        element3.click() // клик по элементу

        TimeUnit.SECONDS.sleep(5)
    }


}