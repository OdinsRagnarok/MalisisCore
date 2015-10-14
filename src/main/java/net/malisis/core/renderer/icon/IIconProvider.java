/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Ordinastie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.malisis.core.renderer.icon;

import java.util.function.Consumer;

import net.malisis.core.MalisisRegistry;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.fml.common.registry.GameData;

/**
 * The IIconProvider interface allows to pass {@link MalisisIcon} to the rendering processes.<br>
 * Implementers of this interface can be registered with {@link MalisisRegistry#registerIconProvider(IIconProvider)} so that icons used can
 * be registered and stitched to the {@link TextureMap}.
 *
 * @author Ordinastie
 */
public interface IIconProvider
{

	/**
	 * Gets the {@link MalisisIcon} to use.
	 *
	 * @return the icon
	 */
	public MalisisIcon getIcon();

	/**
	 * Registers the icons to the {@link TextureMap}.
	 *
	 * @param map the map
	 */
	public void registerIcons(TextureMap map);

	public static void registerIconProviders()
	{
		Consumer<?> consumer = (obj) -> {
			if (obj instanceof IMetaIconProvider)
				MalisisRegistry.registerIconProvider(((IMetaIconProvider) obj).getIconProvider());
		};

		GameData.getBlockRegistry().forEach(consumer);
		GameData.getItemRegistry().forEach(consumer);
	}
}