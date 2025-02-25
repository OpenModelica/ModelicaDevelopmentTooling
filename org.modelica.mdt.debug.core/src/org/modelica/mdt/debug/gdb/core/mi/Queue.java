/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Linkoping University,
 * Department of Computer and Information Science,
 * SE-58183 Linkoping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 
 * AND THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S  
 * ACCEPTANCE OF THE OSMC PUBLIC LICENSE.
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from Linkoping University, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS
 * OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 */
package org.modelica.mdt.debug.gdb.core.mi;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple thread-safe Queue implementation.
 * 
 * @author Adeel Asghar
 *
 */
public class Queue {
	
	protected List<Object> list;
	
	public Queue() {
		list = Collections.synchronizedList(new LinkedList<Object>());
	}
	
	/**
	 * Removes the item from the list and return it. 
	 */
	public Object removeItem() throws InterruptedException {
		synchronized (list) {
			while (list.isEmpty()) {
				list.wait();
			}

			// extract the new first cmd
			Object item = list.remove(0);
			return item;
		}
	}

	/**
	 * Adds the item to the list and notify all waiting threads.
	 * @param item
	 */
	public void addItem(Object item) {
		synchronized (list) {
			list.add(item);
			// After adding, notify any and all waiting
			// threads that the list has changed.
			list.notifyAll();
		}
	}

	/**
	 * Clear the items from the list and return the preserved list.
	 * @return
	 */
	public Object[] clearItems() {
		Object[] array;
		synchronized (list) {
			array = list.toArray();
			list.clear();
		}
		return array;
	}

	/**
	 * Checks if the list is empty
	 * @return
	 */
	public boolean isEmpty() {
		boolean empty;
		synchronized (list) {
			empty = list.isEmpty();
		}
		return empty;
	}
	
}
