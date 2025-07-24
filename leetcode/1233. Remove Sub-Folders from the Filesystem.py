def removeSubFolder(folder):
    """
    type folder = list[str]
    
    """
    print(len(folder))
    
    # mark = []

    # for i in folder:


    # for i in range(len(folder)-1):
    #     for j in range(i+1,len(folder)-1):
    #             # print(i)
    #             # print(j)
    #             if(folder[i] in (folder[j])):
    #                 if(len(folder[i]) > len(folder[j])):
    #                     # del(folder[i])
    #                     mark.append(folder(j))
    #                 else:
    #                     mark.append(folder(j))
    folder.sort()
    print(folder)
    mark =[]
    for i in folder:
        if(not mark or not i.startswith(mark[-1] + "/")):
            mark.append(i)
    return mark   
    # print(mark)
                         

# removeSubFolder(["/a","/a/b","/a/b/c"])

print(removeSubFolder(["/a/b","/a/b/c","/c/b","/a"]))

print(removeSubFolder(["/ah/al/am","/ah/al"]))