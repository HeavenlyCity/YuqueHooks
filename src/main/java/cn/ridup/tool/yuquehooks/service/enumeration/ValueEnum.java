package cn.ridup.tool.yuquehooks.service.enumeration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.util.Assert;

/**
 * Interface for value enum.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/9 20:19
 */
public interface ValueEnum<T> {

    /**
     * Gets enum value.
     *
     * @return enum value
     */
    T getValue();

    /**
     * Converts value to corresponding enum.
     *
     * @param enumType enum type
     * @param value database value
     * @param <V> value generic
     * @param <E> enum generic
     * @return corresponding enum
     */
    static <V, E extends Enum<E> & ValueEnum<V>> E valueToEnum(Class<E> enumType, V value) {
        Assert.notNull(enumType, "enum type must not be null");
        Assert.notNull(value, "value must not be null");
        Assert.isTrue(enumType.isEnum(), "type must be an enum type");
        return Stream.of(enumType.getEnumConstants())
            .filter(item -> value.equals(item.getValue()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("unknown database value: " + value));
    }
}
