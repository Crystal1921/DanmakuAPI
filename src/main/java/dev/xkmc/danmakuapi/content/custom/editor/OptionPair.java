package dev.xkmc.danmakuapi.content.custom.editor;

import dev.xkmc.danmakuapi.content.custom.annotation.ArgField;

public record OptionPair(ArgField field, ValueProvider<?> option) {

}
