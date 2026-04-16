class Solution(object):
    def groupThePeople(self, groupSizes):
        """
        :type groupSizes: List[int]
        :rtype: List[List[int]]
        """
        groups = {}
        result = []

        for person_id, size in enumerate(groupSizes):
            if size not in groups:
                groups[size] = []
            
            groups[size].append(person_id)

            if len(groups[size]) == size:
                result.append(groups[size])
                groups[size] = []
                
        return result
