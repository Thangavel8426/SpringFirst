import React, { createContext, useContext, useState, useCallback } from 'react';

const SearchContext = createContext();

export const SearchProvider = ({ children }) => {
  const [searchTerm, setSearchTerm] = useState('');
  return (
    <SearchContext.Provider value={{ searchTerm, setSearchTerm }}>
      {children}
    </SearchContext.Provider>
  );
};

export const useSearch = () => useContext(SearchContext);

// --- Experiment Events Context ---
const ExperimentContext = createContext();

export const ExperimentProvider = ({ children }) => {
  const [refreshCount, setRefreshCount] = useState(0);
  const triggerRefresh = useCallback(() => setRefreshCount(c => c + 1), []);
  return (
    <ExperimentContext.Provider value={{ refreshCount, triggerRefresh }}>
      {children}
    </ExperimentContext.Provider>
  );
};

export const useExperimentEvents = () => useContext(ExperimentContext); 