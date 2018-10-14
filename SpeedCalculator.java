package Evil_Code_HorseOwners;

import org.bukkit.entity.Entity;
import EvLib2.ReflectionUtils;
import EvLib2.ReflectionUtils.RefClass;
import EvLib2.ReflectionUtils.RefMethod;

public class SpeedCalculator {
	private static final RefClass classCraftLivingEntity = ReflectionUtils.getRefClass("{cb}.entity.CraftLivingEntity");
	private static final RefClass classEntityInsentient = ReflectionUtils.getRefClass("{nms}.EntityInsentient");
	private static final RefClass classGenericAttributes = ReflectionUtils.getRefClass("{nms}.GenericAttributes");
	private static final RefClass classIAttribute = ReflectionUtils.getRefClass("{nms}.IAttribute");
	private static final RefClass classAttributeInstance = ReflectionUtils.getRefClass("{nms}.AttributeInstance");
	private RefMethod methodGetHandle = classCraftLivingEntity.getMethod("getHandle");
	private RefMethod methodGetAttributeInstance = classEntityInsentient.getMethod("getAttributeInstance", classIAttribute);
	private Object movementSpeedEnumVal = classGenericAttributes.getField("MOVEMENT_SPEED").of(null).get();
	private RefMethod methodGetValue = classAttributeInstance.getMethod("getValue");
	private RefMethod methodSetValue = classAttributeInstance.getMethod("setValue", double.class);
	
	public double getHorseSpeed(Entity h){
		Object handle = methodGetHandle.of(h).call();
		Object attribute = methodGetAttributeInstance.of(handle).call(movementSpeedEnumVal);
		return (Double) methodGetValue.of(attribute).call();
	}
	public void setHorseSpeed(Entity h, double speed){
		//use about .225 for normalish speed
		Object handle = methodGetHandle.of(h).call();
		Object attribute = methodGetAttributeInstance.of(handle).call(movementSpeedEnumVal);
		methodSetValue.of(attribute).call(speed);
	}
}
