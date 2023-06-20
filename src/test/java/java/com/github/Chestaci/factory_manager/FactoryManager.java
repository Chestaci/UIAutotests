package java.com.github.Chestaci.factory_manager;

import com.github.Chestaci.factory_manager.factory.DriverFactory;
import com.github.Chestaci.factory_manager.factory.impl.LocalDriverFactory;
import com.github.Chestaci.factory_manager.factory.impl.RemoteDriverFactory;

/**
 * Класс, управляющий выбором фабрики
 */
public class FactoryManager {
    public static DriverFactory getFactory(boolean isRemote) {
        if (isRemote) {
            return new RemoteDriverFactory();
        } else {
            return new LocalDriverFactory();
        }
    }
}
