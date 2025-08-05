class Node:
    def __init__(self,data=None):
        self.data = data
        self.next = None

    def getData(self):
        return self.data



class LinkedList:
    def __init__(self):
        self.head = None
        self.tail = None

    def begin(self):
        return self.head
    

    def push_front(self,data):
        new_node = Node(data)
        if(self.head == None):
            self.head = self.tail = new_node
        else:
            new_node.next = self.head
            self.head = new_node

    def push_back(self,data):
        new_node = Node(data)
        if(self.head == None):
            self.head = self.tail = new_node
        else:
            self.tail.next = new_node
            self.tail = new_node


list1 = LinkedList()
list1.push_front(12)
list1.push_front(13)
list1.push_back(14)

temp_head = Node()
temp_head = list1.begin()

while(temp_head != None):
    print(temp_head.getData(),"->", end="")
    temp_head = temp_head.next


