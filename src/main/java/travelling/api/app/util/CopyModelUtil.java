package travelling.api.app.util;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static travelling.api.app.util.ReflectionUtil.get;


public class CopyModelUtil {

    public static <T> T copyOldToNewModel(T oldModel, T newModel) {
        Field[] oldFields = oldModel.getClass().getDeclaredFields();
        Field[] newFields = newModel.getClass().getDeclaredFields();

        try {
            for (int i = 0; i < oldFields.length; i++) {
                if (get(newModel, newFields[i]) == null && get(oldModel, oldFields[i]) != null) {
                    newFields[i].setAccessible(true);
                    newFields[i].set(newModel, get(oldModel, oldFields[i]));
                }
            }

            return newModel;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static <T> boolean checkModelAllFieldIsNull(T model) {
        Field[] fields = model.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                if (get(model, field) != null) {
                    return false;
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return false;
        }

        return true;
    }
}
