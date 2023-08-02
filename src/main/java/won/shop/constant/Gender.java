package won.shop.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    WOMAN("WOMAN","여자"),
    MAN("MAN","남자");

    private final String key;
    private final String name;
}
