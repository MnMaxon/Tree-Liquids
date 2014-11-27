package me.MnMaxon.TreeLiquids;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.StructureGrowEvent;

public class MainListener implements Listener {
	@EventHandler(priority = EventPriority.MONITOR)
	public void onGrow(StructureGrowEvent e) {
		if (e.isCancelled())
			return;
		for (BlockState block : e.getBlocks()) {
			Cuboid cube = new Cuboid(block.getLocation().clone().add(1, 1, 1), block.getLocation().clone()
					.add(-1, -1, -1));
			Iterator<Block> iterator = cube.iterator();
			while (iterator.hasNext()) {
				Block b = iterator.next();
				if (b.getType().equals(Material.STATIONARY_WATER) || b.getType().equals(Material.STATIONARY_LAVA)) {
					b.setType(Material.AIR);
					// 255 , 0
				} else if (b.getType().equals(Material.WATER)) {
					Block closest = getClosest(Material.STATIONARY_WATER, b.getLocation().clone());
					if (closest == null)
						b.setType(Material.AIR);
					else {
						closest.setType(Material.AIR);
					}
				} else if (b.getType().equals(Material.LAVA)) {
					Block closest = getClosest(Material.STATIONARY_LAVA, b.getLocation().clone());
					if (closest == null)
						b.setType(Material.AIR);
					else {
						closest.setType(Material.AIR);
					}
				}
			}
			e.getLocation();
		}
	}

	private Block getClosest(Material type, Location loc) {
		Location loc1 = loc.clone().add(17, 0, 17);
		loc1.setY(255);
		Location loc2 = loc.clone().add(-17, 0, -17);
		loc2.setY(0);
		Cuboid cube = new Cuboid(loc1, loc2);
		ArrayList<Block> possibleBlocks = new ArrayList<Block>();
		Iterator<Block> iterator = cube.iterator();
		while (iterator.hasNext()) {
			Block b = iterator.next();
			if (b.getType().equals(type))
				possibleBlocks.add(b);
		}
		Block closest = null;
		for (Block b : possibleBlocks)
			if (closest == null || b.getLocation().distance(loc) < closest.getLocation().distance(loc))
				closest = b;
		return closest;
	}
}
