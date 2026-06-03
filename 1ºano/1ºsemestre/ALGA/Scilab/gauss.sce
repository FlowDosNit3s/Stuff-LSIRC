function[L,U]=gauss(A)

[n,m]=size(A);
    
L=eye(n,n);  
    
for i=1:n-1 
        
    for j=i+1:n 
          
        mu=A(j,i)/A(i,i);   
      
            for k=1:n 
                           
                A(j,k)=-A(i,k)*mu+A(j,k);   
                         
            end   
                 
            L(j,i)=mu;
            
    end
end
    U=A;       
endfunction
 
